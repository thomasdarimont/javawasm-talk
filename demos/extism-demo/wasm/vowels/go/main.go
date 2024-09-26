package main

import (
	"strconv"

	"github.com/extism/go-pdk"
)

// build via
// tinygo build -o code.wasm -target wasi code.go

//export count_vowels
func count_vowels() int32 {

	// read input as []byte slice
	input := pdk.Input()

	count := 0
	for _, a := range input {
		switch a {
		case 'A', 'I', 'E', 'O', 'U', 'a', 'e', 'i', 'o', 'u':
			count++
		default:
		}
	}

	// test some extra pdk functionality
	message, ok := pdk.GetConfig("message")

	if !ok {
		message = "<unset by host>"
	}

	output := `{"count": ` + strconv.Itoa(count) + `, "message": "` + message + `"}`
	mem := pdk.AllocateString(output)

	// zero-copy output to host
	pdk.OutputMemory(mem)

	return 0
}

func main() {}
