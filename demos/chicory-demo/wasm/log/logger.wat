(module
  ;; Import console.log. Takes a ptr and a length to the string in memory
  (import "console" "log" (func $log (param i32) (param i32))) ;; console_log imported as $log

  (func $logIt (param $count i32)
    (local $var i32)
    ;; some random nops and drop, not important
    nop
    nop
    i32.const 1
    drop
    ;; start implementation
    ;; call console.log("Hello, World!") $count times
    (loop
      i32.const 13
      i32.const 0
      call $log
      local.get $count
      i32.const 1
      i32.sub
      local.tee $count
      br_if 0
    )
  )

  (export "logIt" (func $logIt))

  (memory 1)
  (data $.rodata (i32.const 0) "Hello, World!\00") ;; 13 characters
)