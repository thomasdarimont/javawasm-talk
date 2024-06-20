package demo;

import com.dylibso.chicory.runtime.ExportFunction;
import com.dylibso.chicory.runtime.Module;
import com.dylibso.chicory.wasm.types.Value;

import java.nio.file.Path;

public class ChicoryAddDemo {

    public static void main(String[] args) {

        var wasmPath = Path.of("demos/chicory-demo/wasm/add/rust/add.wasm");
        var instance = Module.builder(wasmPath).build().instantiate();

        ExportFunction addFunc = instance.export("add");

        Value[] input = {Value.i32(3), Value.i32(5)};
        Value[] output = addFunc.apply(input);

        System.out.println(output[0].asLong());
    }
}
