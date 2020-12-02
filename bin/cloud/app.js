var createError = require("http-errors");
var express = require("express");
var path = require("path");
var cookieParser = require("cookie-parser");
var bodyParser = require("body-parser");
var logger = require("morgan");
var fs = require("fs");
var indexRouter = require("./routes/index");
var app = express();
app.set("views", path.join(__dirname, "views"));
app.set("view engine", "ejs");
//app.use(logger("dev"));
app.use(express.json());
app.use(cookieParser());
app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());
app.use(express.static(path.join(__dirname, "public")));
app.use("/", indexRouter);
app.use(function (req, res, next) {
    next(createError(404));
});
app.use(function (err, req, res, next) {
    res.locals.message = err.message;
    res.locals.error = req.app.get("env") === "development" ? err : {};
    res.status(err.status || 500);
    res.render("error");
});
indexRouter.get("/setenabled/:module/:enabled", (req, res) => {
    switch(req.params.module) {
        case "AutoClicker":
        setEnabled(13, req.params.enabled);
        break;
        case "Aimbot":
        setEnabled(14, req.params.enabled);
        break;
        case "NameTags":
        setEnabled(15, req.params.enabled);
        break;
        case "Brightness":
        setEnabled(16, req.params.enabled);
        break;
        case "Reach":
        setEnabled(17, req.params.enabled);
        break;
        case "HitBoxes":
        setEnabled(18, req.params.enabled);
        break;
        case "Velocity":
        setEnabled(19, req.params.enabled);
        break;
        case "FakeLag":
        setEnabled(20, req.params.enabled);
        break;
        case "WTap":
        setEnabled(21, req.params.enabled);
        break;
        case "Self Destruct":
        setEnabled(22, req.params.enabled);
        break;
        case "HUD":
        setEnabled(23, req.params.enabled);
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
    fs.writeFile("./public/plugins", req.body.plugins, function(err) {
        if(err) {
            console.log(err);
        }
    });
    res.send("Settings saved successfully.");
});
indexRouter.post("/self-destruct", (req, res) => {
    setEnabled(22, 1);
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
app.listen(process.argv[2]);
console.log("\x1b[32m%s\x1b[0m", "Starting cloud configuration server.");
console.log("Visit http://localhost:" + process.argv[2] + " on your machine or http://192.168.x.x:" + process.argv[2] + " on an external device to use it.");