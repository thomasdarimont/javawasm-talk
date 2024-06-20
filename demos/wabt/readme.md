$ wasm-interp add.wasm --run-export=add --argument="i32:2" --argument="i32:3"
add(i32:2, i32:3) => i32:5


$ wasm-interp --trace add.wasm --run-export=add --argument="i32:2" --argument="i32:3"
>>> running export "add":
#0.  112: V:2  | local.get $2   
#0.  120: V:3  | local.get $2
#0.  128: V:4  | call $0
#1.   12: V:4  | alloca 1
#1.   20: V:5  | local.get $3
#1.   28: V:6  | local.get $3
#1.   36: V:7  | i32.add 2, 3
#1.   40: V:6  | local.set $2, 5
#1.   48: V:5  | local.get $1
#1.   56: V:6  | drop_keep $3 $1
#1.   68: V:3  | return
#0.  136: V:3  | call $2
#1.   92: V:3  | call $1
#2.   88: V:3  | return
#1.  100: V:3  | call $1
#2.   88: V:3  | return
#1.  108: V:3  | return
#0.  144: V:3  | drop_keep $2 $1
#0.  156: V:1  | return
add(i32:2, i32:3) => i32:5
