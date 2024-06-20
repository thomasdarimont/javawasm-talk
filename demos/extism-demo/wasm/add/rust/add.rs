#![no_main]

use extism_pdk::*;

#[derive(serde::Deserialize, FromBytes)]
#[encoding(Json)]
struct Input {
    a: u32,
    b: u32,
}

#[derive(serde::Serialize, ToBytes)]
#[encoding(Json)]
struct Output {
    value: u32,
}

#[plugin_fn]
pub fn sum(input: Input) -> FnResult<Output> {
    let output = Output { value: input.a + input.b };
    Ok(output)
}