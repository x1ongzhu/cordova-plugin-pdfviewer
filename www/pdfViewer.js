var exec = require('cordova/exec');

exports.open = function (options, success, error) {
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
