
;; call via: wasmtime sum.wat --invoke calc 1 2
(module

  ;; implementation of add function
  (func $add (param $a i32) (param $b i32) (result i32)
    local.get $a ;; push $a param on the stack
    local.get $b ;; push $b param on the stack
    i32.add) ;; pop $a and $b from the stack, compute sum, and push $result on stack

  ;; expose function "calc" which calls our "add" function
  (export "calc" (func $add))
)

