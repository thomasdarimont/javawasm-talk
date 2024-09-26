package demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.extism.sdk.Plugin;
import org.extism.sdk.manifest.Manifest;
import org.extism.sdk.wasm.PathWasmSource;

public class ExtismRustSum {

    public static void main(String[] args) throws Exception {
        var objectMapper = new ObjectMapper();

        var source = new PathWasmSource("code", "demos/extism-demo/wasm/add/rust/add.wasm", null);

        try (var plugin = new Plugin(new Manifest(source), true, null)) {

            Input input = new Input(3, 5);
            String inputString = objectMapper.writeValueAsString(input);
            String outputString = plugin.call("sum", inputString);
            Output result = objectMapper.readValue(outputString, Output.class);

            System.out.println(result.value());
        }
    }

    record Input(int a, int b) {
    }

    record Output(int value) {
    }
}
