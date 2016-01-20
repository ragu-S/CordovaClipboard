// var cordova;
// if(window && !window.cordova) cordova = require('cordova');

/**
 * Clipboard plugin for Cordova
 * 
 * @constructor
 */
//function Clipboard () {}

/**
 * Sets the clipboard content
 *
 * @param {String}   text      The content to copy to the clipboard
 * @param {Function} onSuccess The function to call in case of success (takes the copied text as argument)
 * @param {Function} onFail    The function to call in case of error
 */
// Clipboard.prototype.copy = function (text, onSuccess, onFail) {
//     if (typeof text === "undefined" || text === null) text = "";
// 	cordova.exec(onSuccess, onFail, "Clipboard", "copy", [text]);
// };

/**
 * Gets the clipboard content
 *
 * @param {Function} onSuccess The function to call in case of success
 * @param {Function} onFail    The function to call in case of error
 */
// Clipboard.prototype.paste = function (onSuccess, onFail) {
// 	cordova.exec(onSuccess, onFail, "Clipboard", "paste", []);
// };

// Register the plugin
//var clipboard = new Clipboard();
//module.exports = clipboard;
var exec = require("cordova/exec");

module.exports = {
	copy: function (text, onSuccess, onFail) {
    		if (typeof text === "undefined" || text === null) text = "";
		exec(onSuccess, onFail, "Clipboard", "copy", [text]);
	},
	paste: function (onSuccess, onFail) {
		exec(onSuccess, onFail, "Clipboard", "paste", []);
	}
};
