package ai.rorsch.moduleplugins.my_module;

import android.content.Context;
import ai.rorsch.pandagenie.module.runtime.ModulePlugin;
import org.json.JSONObject;

public class MyModulePlugin implements ModulePlugin {

    @Override
    public String invoke(Context context, String action, String paramsJson) throws Exception {
        JSONObject params = new JSONObject(paramsJson != null && !paramsJson.isEmpty() ? paramsJson : "{}");

        switch (action) {
            case "hello":
                return hello(params);
            case "doTask":
                return doTask(context, params);
            default:
                return error("Unsupported action: " + action);
        }
    }

    private String hello(JSONObject params) throws Exception {
        String name = params.optString("name", "World");
        return new JSONObject()
                .put("success", true)
                .put("output", "Hello, " + name + "! This module is working.")
                .toString();
    }

    private String doTask(Context context, JSONObject params) throws Exception {
        String input = params.optString("input", "");
        if (input.isEmpty()) {
            return error("Input is required");
        }

        // TODO: Replace with your actual logic
        String result = "Processed: " + input;

        return new JSONObject()
                .put("success", true)
                .put("output", result)
                .toString();
    }

    private String error(String message) throws Exception {
        return new JSONObject()
                .put("success", false)
                .put("error", message)
                .toString();
    }
}
