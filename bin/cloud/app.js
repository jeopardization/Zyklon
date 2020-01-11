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
app.locals.nametagsEnabled = false;
app.locals.nametagsBind = 0;
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
app.locals.refreshRate = 0;
app.locals.friends = [];
app.locals.enemies = [];
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
        app.locals.nametagsBind = values[2];
        app.locals.brightnessBind = values[3];
        app.locals.reachBind = values[4];
        app.locals.hitboxesBind = values[5];
        app.locals.wtapBind = values[6];
        app.locals.selfDestructBind = values[7];
        app.locals.HUDBind = values[8];
        app.locals.throwpotBind = values[9];
        app.locals.refillBind = values[10];
        app.locals.autoclickerEnabled = values[11];
        app.locals.aimbotEnabled = values[12];
        app.locals.nametagsEnabled = values[13];
        app.locals.brightnessEnabled = values[14];
        app.locals.reachEnabled = values[15];
        app.locals.hitboxesEnabled = values[16];
        app.locals.wtapEnabled = values[17];
        app.locals.HUDEnabled = values[19];
        app.locals.autoclickerReqItem = values[20];
        app.locals.aimbotReqItem = values[21];
        app.locals.reachReqItem = values[22];
        app.locals.hitboxesReqItem = values[23];
        app.locals.wtapReqItem = values[24];
        app.locals.autoclickerItemWhitelist = values[25];
        app.locals.aimbotItemWhitelist = values[26];
        app.locals.reachItemWhitelist = values[27];
        app.locals.hitboxesItemWhitelist = values[28];
        app.locals.wtapItemWhitelist = values[29];
        app.locals.autoclickerMinCPS = values[30];
        app.locals.autoclickerMaxCPS = values[31];
        app.locals.aimbotDist = values[32];
        app.locals.aimbotFOV = values[33];
        app.locals.aimbotReqMouse = values[34];
        app.locals.aimbotMinYawSmooth = values[35];
        app.locals.aimbotMaxYawSmooth = values[36];
        app.locals.aimbotMinPitchSmooth = values[37];
        app.locals.aimbotMaxPitchSmooth = values[38];
        app.locals.aimbotMinRand = values[39];
        app.locals.aimbotMaxRand = values[40];
        app.locals.reachMinExpansion = values[41];
        app.locals.reachMaxExpansion = values[42];
        app.locals.hitboxesExpansion = values[43];
        app.locals.wtapDist = values[44];
        app.locals.wtapMinTapDelay = values[45];
        app.locals.wtapMaxTapDelay = values[46];
        app.locals.throwpotMinThrowDelay = values[47];
        app.locals.throwpotMaxThrowDelay = values[48];
        app.locals.refillMinFillDelay = values[49];
        app.locals.refillMaxFillDelay = values[50];
        app.locals.refillMinExitDelay = values[51];
        app.locals.refillMaxExitDelay = values[52];
        app.locals.refreshRate = values[53];
    } catch (ex) {
        console.log(ex);
    }
});
fs.readFile("./public/players", "utf8", (err, data) => {
    try {
        var values = data.split(";");
        app.locals.friends = values[0];
        app.locals.enemies = values[1];
    } catch (ex) {
        console.log(ex);
    }
});
indexRouter.get("/setenabled/:module/:enabled", (req, res) => {
    switch(req.params.module) {
        case "AutoClicker":
        setEnabled(11, req.params.enabled);
        break;
        case "Aimbot":
        setEnabled(12, req.params.enabled);
        break;
        case "NameTags":
        setEnabled(13, req.params.enabled);
        break;
        case "Brightness":
        setEnabled(14, req.params.enabled);
        break;
        case "Reach":
        setEnabled(15, req.params.enabled);
        break;
        case "HitBoxes":
        setEnabled(16, req.params.enabled);
        break;
        case "WTap":
        setEnabled(17, req.params.enabled);
        break;
        case "Self Destruct":
        setEnabled(18, req.params.enabled);
        break;
        case "HUD":
        setEnabled(19, req.params.enabled);
        break;
    }
    res.end();
});
indexRouter.post("/apply", (req, res) => {
    fs.writeFile("./public/config", req.body.config, function(err) {
        if(err) {
            console.log(err);
        }
    });
    fs.writeFile("./public/players", req.body.players, function(err) {
        if(err) {
            console.log(err);
        }
    });
    res.send("Settings saved successfully.");
});
indexRouter.post("/self-destruct", (req, res) => {
    setEnabled(18, 1);
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