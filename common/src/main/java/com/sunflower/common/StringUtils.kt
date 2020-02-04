package com.sunflower.common

import java.text.Normalizer

fun normalize(input: String): String = Normalizer
    .normalize(input, Normalizer.Form.NFD)
    .replace("[^\\p{ASCII}]".toRegex(), "")
