package demo;

import org.extism.sdk.Plugin;
import org.extism.sdk.manifest.Manifest;
import org.extism.sdk.wasm.PathWasmSource;

public class ExtismGoVowels {

    public static void main(String[] args) {

        var source = new PathWasmSource("code", "demos/extism-demo/wasm/vowels/go/vowels.wasm", null);

        try (var plugin = new Plugin(new Manifest(source), true, null)) {
            // counts number of vowels in the given input
            var output = plugin.call("count_vowels", "Hello World");
            System.out.println(output);
        }
    }
}
