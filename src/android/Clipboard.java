package com.verso.cordova.clipboard;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.ClipboardManager;
import android.content.ClipData;
import android.content.ClipDescription;

public class Clipboard extends CordovaPlugin {

    private static final String actionCopy = "copy";
    private static final String actionPaste = "paste";
    private static CallbackContext cb;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        cb = callbackContext;
        final String _action = action;
        final JSONArray _args = args;

        cordova.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(cb != null) {
                    pluginAction(_action, _args);
                }
            }
        });

        return true;
    }
    private void pluginAction(final String action, final JSONArray args) {
        ClipboardManager clipboard = (ClipboardManager) cordova.getActivity().getSystemService(Context.CLIPBOARD_SERVICE);

        if (action.equals(actionCopy)) {
            try {
                final String text = args.getString(0);
                ClipData clip = ClipData.newPlainText("Text", text);

                clipboard.setPrimaryClip(clip);

            } catch (JSONException e) {
                cb.sendPluginResult(new PluginResult(PluginResult.Status.JSON_EXCEPTION));
            } catch (Exception e) {
                cb.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, e.toString()));
            }
        } else if (action.equals(actionPaste)) {
            if (!clipboard.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
                cb.sendPluginResult(new PluginResult(PluginResult.Status.NO_RESULT));
            }
            try {
                ClipData.Item item = clipboard.getPrimaryClip().getItemAt(0);
                String text = item.getText().toString();

                if (text == null) text = "";

                cb.success(text);

            } catch (Exception e) {
                cb.sendPluginResult(new PluginResult(PluginResult.Status.ERROR, e.toString()));
            }
        }
    }
}


