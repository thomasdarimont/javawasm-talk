package demo;

import io.github.kawamuray.wasmtime.Module;
import io.github.kawamuray.wasmtime.Instance;
import io.github.kawamuray.wasmtime.Store;
import io.github.kawamuray.wasmtime.Val;
import io.github.kawamuray.wasmtime.WasmValType;

import java.util.Collections;

public class JavaWasmtimeSumDemo {

    public static void main(String[] args) {

        try (var store = Store.withoutData(); //
             var engine = store.engine(); //
             var module = Module.fromFile(engine, WasmIO.locateWatFromClasspath("sum.wasm").toFile().getAbsolutePath()); //
             var instance = new Instance(store, module, Collections.emptyList()); //
             var func = instance.getFunc(store, "calc").orElseThrow()) {

            var results = func.call(store, WasmValType.I32.toWasmVal(3), WasmValType.I32.toWasmVal(4));

            var result = (Val) results[0];

            System.out.printf("Result: %s", result.i32());
        }
    }
}