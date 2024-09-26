package demo;

import com.dylibso.chicory.runtime.HostFunction;
import com.dylibso.chicory.runtime.HostImports;
import com.dylibso.chicory.runtime.Instance;
import com.dylibso.chicory.runtime.Module;
import com.dylibso.chicory.wasm.types.Value;
import com.dylibso.chicory.wasm.types.ValueType;

import java.nio.file.Path;
import java.util.List;

public class ChicoryHostFunctionDemo {

    public static void main(String[] args) {

        // this is called from web assembly!
        var func = new HostFunction((Instance instance, Value... inputs) -> {
            // read message from WASM instance
            var len = inputs[0].asInt();
            var offset = inputs[1].asInt();
            var message = instance.memory().readString(offset, len);

            System.out.printf("### %s%n", message);
            return null;
        }, "console", "log", List.of(ValueType.I32, ValueType.I32), List.of());

        var instance = Module.builder(Path.of("demos/chicory-demo/wasm/log/logger.wasm"))
                .withHostImports(new HostImports(new HostFunction[]{func})) // expose func to WASM runtime
                .build()
                .instantiate();

        var logIt = instance.export("logIt");

        // this calls a web assembly function
        logIt.apply(Value.i32(5)); // Prints the message hello world 5 times
    }
}

