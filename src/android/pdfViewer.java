package com.x1ongzhu.pdfviewer;

import android.content.Intent;
import android.os.Bundle;

import org.apache.cordova.BuildConfig;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.x;


/**
 * This class echoes a string called from JavaScript.
 */
public class pdfViewer extends CordovaPlugin {


    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        x.Ext.init(cordova.getActivity().getApplication());
        x.Ext.setDebug(BuildConfig.DEBUG);
        if (action.equals("open")) {
            this.open(args, callbackContext);
            return true;
        }
        return false;
    }

    private void open(JSONArray args, CallbackContext callbackContext) {
        JSONObject params = null;
        String url = null;
        try {
            params = args.getJSONObject(0);
            url = params.getString("url");
        } catch (JSONException e) {
            e.printStackTrace();
            callbackContext.error("invalid arguments");
            return;
        }

        Intent intent = new Intent(cordova.getActivity(), PDFActivity.class);
        intent.putExtra("params", params.toString());
        cordova.getActivity().startActivity(intent);
    }
}
