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
app.locals.reachBind = 0;
app.locals.reachEnabled = false;
app.locals.reachMinExpansion = 0;
app.locals.reachMaxExpansion = 0;
app.locals.reachReqItem = false;
app.locals.reachItemWhitelist = [];
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
        app.locals.reachBind = values[3];
        app.locals.hitboxesBind = values[4];
        app.locals.wtapBind = values[5];
        app.locals.selfDestructBind = values[6];
        app.locals.HUDBind = values[7];
        app.locals.throwpotBind = values[8];
        app.locals.refillBind = values[9];
        app.locals.autoclickerEnabled = values[10];
        app.locals.aimbotEnabled = values[11];
        app.locals.brightnessEnabled = values[12];
        app.locals.reachEnabled = values[13];
        app.locals.hitboxesEnabled = values[14];
        app.locals.wtapEnabled = values[15];
        app.locals.HUDEnabled = values[17];
        app.locals.autoclickerReqItem = values[18];
        app.locals.aimbotReqItem = values[19];
        app.locals.reachReqItem = values[20];
        app.locals.hitboxesReqItem = values[21];
        app.locals.wtapReqItem = values[22];
        app.locals.autoclickerItemWhitelist = values[23];
        app.locals.aimbotItemWhitelist = values[24];
        app.locals.reachItemWhitelist = values[25];
        app.locals.hitboxesItemWhitelist = values[26];
        app.locals.wtapItemWhitelist = values[27];
        app.locals.autoclickerMinCPS = values[28];
        app.locals.autoclickerMaxCPS = values[29];
        app.locals.aimbotDist = values[30];
        app.locals.aimbotFOV = values[31];
        app.locals.aimbotReqMouse = values[32];
        app.locals.aimbotMinYawSmooth = values[33];
        app.locals.aimbotMaxYawSmooth = values[34];
        app.locals.aimbotMinPitchSmooth = values[35];
        app.locals.aimbotMaxPitchSmooth = values[36];
        app.locals.aimbotMinRand = values[37];
        app.locals.aimbotMaxRand = values[38];
        app.locals.reachMinExpansion = values[39];
        app.locals.reachMaxExpansion = values[40];
        app.locals.hitboxesExpansion = values[41];
        app.locals.wtapDist = values[42];
        app.locals.wtapMinTapDelay = values[43];
        app.locals.wtapMaxTapDelay = values[44];
        app.locals.throwpotMinThrowDelay = values[45];
        app.locals.throwpotMaxThrowDelay = values[46];
        app.locals.refillMinFillDelay = values[47];
        app.locals.refillMaxFillDelay = values[48];
        app.locals.refillMinExitDelay = values[49];
        app.locals.refillMaxExitDelay = values[50];
    } catch (ex) {
        console.log(ex);
    }
});
indexRouter.get("/setenabled/:module/:enabled", (req, res) => {
    switch(req.params.module) {
        case "AutoClicker":
        setEnabled(10, req.params.enabled);
        break;
        case "Aimbot":
        setEnabled(11, req.params.enabled);
        break;
        case "Brightness":
        setEnabled(12, req.params.enabled);
        break;
        case "Reach":
        setEnabled(13, req.params.enabled);
        break;
        case "HitBoxes":
        setEnabled(14, req.params.enabled);
        break;
        case "WTap":
        setEnabled(15, req.params.enabled);
        break;
        case "Self Destruct":
        setEnabled(16, req.params.enabled);
        break;
        case "HUD":
        setEnabled(17, req.params.enabled);
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
    setEnabled(16, 1);
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