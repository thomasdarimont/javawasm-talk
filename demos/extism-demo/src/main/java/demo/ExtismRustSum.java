package demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.extism.sdk.Plugin;
import org.extism.sdk.manifest.Manifest;
import org.extism.sdk.wasm.PathWasmSource;

public class ExtismRustSum {

    public static void main(String[] args) throws Exception {
        var json = new ObjectMapper();
        var source = new PathWasmSource("code", "demos/extism-demo/wasm/add/rust/add.wasm", null);

        try (var plugin = new Plugin(new Manifest(source), true, null)) {

            String input = json.writeValueAsString(new Input(3, 5));
            String output = plugin.call("sum", input);
            Output result = json.readValue(output, Output.class);

            System.out.println(result.value());
        }
    }

    record Input(int a, int b) {
    }

    record Output(int value) {
    }
}
