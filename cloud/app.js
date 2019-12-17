var createError = require("http-errors");
var express = require("express");
var path = require("path");
var cookieParser = require("cookie-parser");
var bodyParser = require("body-parser");
var logger = require("morgan");
var fs = require("fs");
var indexRouter = require("./routes/index");
var usersRouter = require("./routes/users");
var app = express();
app.locals.autoclickerBind = 0;
app.locals.autoclickerEnabled = false;
app.locals.autoclickerReqItem = false;
app.locals.autoclickerMinCPS = 0;
app.locals.autoclickerMaxCPS = 0;
app.locals.autoclickerItemWhitelist = [];
app.locals.aimbotBind = 0;
app.locals.aimbotEnabled = false;
app.locals.aimbotReqItem = false;
app.locals.aimbotReqMouse = false;
app.locals.aimbotMinYawSmooth = 0;
app.locals.aimbotMaxYawSmooth = 0;
app.locals.aimbotMinPitchSmooth = 0;
app.locals.aimbotMaxPitchSmooth = 0;
app.locals.aimbotMinRand = 0;
app.locals.aimbotMaxRand = 0;
app.locals.aimbotDist = 0;
app.locals.aimbotFOV = 0;
app.locals.aimbotItemWhitelist = [];
app.locals.throwpotBind = 0;
app.locals.throwpotMinThrowDelay = 0;
app.locals.throwpotMaxThrowDelay = 0;
app.locals.refillBind = 0;
app.locals.refillMinFillDelay = 0;
app.locals.refillMaxFillDelay = 0;
app.locals.refillMinExitDelay = 0;
app.locals.refillMaxExitDelay = 0;
app.locals.brightnessEnabled = false;
app.locals.brightnessBind = 0;
app.locals.HUDEnabled = false;
app.locals.HUDBind = 0;
app.locals.wtapBind = 0;
app.locals.wtapEnabled = false;
app.locals.wtapMinTapDelay = 0;
app.locals.wtapMaxTapDelay = 0;
app.locals.wtapDist = 0;
app.locals.wtapReqItem = false;
app.locals.wtapItemWhitelist = [];
app.locals.hitboxesBind = 0;
app.locals.hitboxesEnabled = false;
app.locals.hitboxesExpansion = 0;
app.locals.hitboxesReqItem = false;
app.locals.hitboxesItemWhitelist = [];
app.locals.selfDestructBind = 0;
app.set("views", path.join(__dirname, "views"));
app.set("view engine", "ejs");
app.use(logger("dev"));
app.use(express.json());
app.use(cookieParser());
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());
app.use(express.static(path.join(__dirname, "public")));
app.use("/", indexRouter);
app.use("/users", usersRouter);
app.use(function (req, res, next) {
    next(createError(404));
});
app.use(function (err, req, res, next) {
    res.locals.message = err.message;
    res.locals.error = req.app.get("env") === "development" ? err : {};
    res.status(err.status || 500);
    res.render("error");
});
fs.readFile("./public/config", "utf8", (err, data) => {
    try {
        var values = data.split(";");
        app.locals.autoclickerBind = values[0];
        app.locals.aimbotBind = values[1];
        app.locals.brightnessBind = values[2];
        app.locals.hitboxesBind = values[3];
        app.locals.wtapBind = values[4];
        app.locals.selfDestructBind = values[5];
        app.locals.HUDBind = values[6];
        app.locals.throwpotBind = values[7];
        app.locals.refillBind = values[8];
        app.locals.autoclickerEnabled = values[9];
        app.locals.aimbotEnabled = values[10];
        app.locals.brightnessEnabled = values[11];
        app.locals.hitboxesEnabled = values[12];
        app.locals.wtapEnabled = values[13];
        app.locals.HUDEnabled = values[15];
        app.locals.autoclickerReqItem = values[16];
        app.locals.aimbotReqItem = values[17];
        app.locals.hitboxesReqItem = values[18];
        app.locals.wtapReqItem = values[19];
        app.locals.autoclickerItemWhitelist = values[20];
        app.locals.aimbotItemWhitelist = values[21];
        app.locals.hitboxesItemWhitelist = values[22];
        app.locals.wtapItemWhitelist = values[23];
        app.locals.autoclickerMinCPS = values[24];
        app.locals.autoclickerMaxCPS = values[25];
        app.locals.aimbotDist = values[26];
        app.locals.aimbotFOV = values[27];
        app.locals.aimbotReqMouse = values[28];
        app.locals.aimbotMinYawSmooth = values[29];
        app.locals.aimbotMaxYawSmooth = values[30];
        app.locals.aimbotMinPitchSmooth = values[31];
        app.locals.aimbotMaxPitchSmooth = values[32];
        app.locals.aimbotMinRand = values[33];
        app.locals.aimbotMaxRand = values[34];
        app.locals.hitboxesExpansion = values[35];
        app.locals.wtapDist = values[36];
        app.locals.wtapMinTapDelay = values[37];
        app.locals.wtapMaxTapDelay = values[38];
        app.locals.throwpotMinThrowDelay = values[39];
        app.locals.throwpotMaxThrowDelay = values[40];
        app.locals.refillMinFillDelay = values[41];
        app.locals.refillMaxFillDelay = values[42];
        app.locals.refillMinExitDelay = values[43];
        app.locals.refillMaxExitDelay = values[44];
    } catch (ex) {
        console.log(ex);
    }
});
indexRouter.get("/setenabled/:module/:enabled", (req, res) => {
    switch(req.params.module) {
        case "AutoClicker":
        setEnabled(9, req.params.enabled);
        break;
        case "Aimbot":
        setEnabled(10, req.params.enabled);
        break;
        case "Brightness":
        setEnabled(11, req.params.enabled);
        break;
        case "HitBoxes":
        setEnabled(12, req.params.enabled);
        break;
        case "WTap":
        setEnabled(13, req.params.enabled);
        break;
        case "Self Destruct":
        setEnabled(14, req.params.enabled);
        break;
        case "HUD":
        setEnabled(15, req.params.enabled);
        break;
    }
    res.end();
});
indexRouter.post("/apply", (req, res) => {
    fs.writeFile("./public/config", req.body.config, function(err) {
        err ? console.log(err) : res.send("Settings were saved.");
    });
});
indexRouter.post("/self-destruct", (req, res) => {
    setEnabled(14, 1);
    res.send("Client will be self destructed.")
});
function setEnabled(index, enabled) {
    fs.readFile("./public/config", "utf8", (err, data) => {
        var count = 0;
        var pos = 0;
        for(var i = 0; i < data.length; i++) {
            if(String(data).charAt(i) == ';') {
                count++;
                if(count == index) {
                    pos = i + 1;
                    break;
                }
            }
        }
        fs.writeFile("./public/config", data.substr(0, pos) + enabled + data.substr(pos + 1, data.length), function(err) {
            if(err) {
                console.log(err);
            }
        });
    });
}
module.exports = app;
app.listen(1337);