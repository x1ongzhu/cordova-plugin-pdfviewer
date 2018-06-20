var exec = require('cordova/exec');

exports.open = function (url, success, error, options) {
    if (!options) {
        options = {};
    }
    options.url = url;
    if (!success) {
        success = function (res) {
            console.log(res)
        }
    }
    if (!error) {
        error = function (err) {
            console.log(err)
        }
    }
    exec(success, error, 'pdfViewer', 'open', [options]);
};
