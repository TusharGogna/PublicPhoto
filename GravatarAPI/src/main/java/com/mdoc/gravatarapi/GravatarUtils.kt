package com.mdoc.gravatarapi

import java.io.UnsupportedEncodingException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

private const val baseURL = "https://gravatar.com/avatar/"
private fun hex(array: ByteArray): String {
    val sb = StringBuffer()
    for (i in array.indices) {
        sb.append(
            Integer.toHexString(
                (array[i]
                    .toInt()
                        and 0xFF) or 0x100
            ).substring(1, 3)
        )
    }
    return sb.toString()
}

private fun md5Hex(message: String): String? {
    try {
        val md = MessageDigest.getInstance("MD5")
        return hex(md.digest(message.toByteArray(charset("CP1252"))))
    } catch (_: NoSuchAlgorithmException) {
    } catch (_: UnsupportedEncodingException) {
    }
    return null
}


fun getPublicPhoto(email: String,
                   size : Int = 240,
                   isDefault: Boolean = true,
                   defaultType: PlaceHolderType = PlaceHolderType.ROBOHASH
): String{
    val defaultURL = if(isDefault){
        "&d=${defaultType.name.lowercase()}"
    }else{
        ""
    }
    return baseURL+ md5Hex(email) + "?s=$size$defaultURL"
}

/*
PlaceHolderType:
mp: (mystery-person) a simple, cartoon-style silhouetted outline of a person (does not vary by email hash)
identicon: a geometric pattern based on an email hash
monsterid: a generated ‘monster’ with different colors, faces, etc
wavatar: generated faces with differing features and backgrounds
retro: awesome generated, 8-bit arcade-style pixelated faces
robohash: a generated robot with different colors, faces, etc
blank: a transparent PNG image (border added to HTML below for demonstration purposes)
* */
enum class PlaceHolderType{
    MP,
    IDENTICON,
    MONSTERID,
    WAVATAR,
    RETRO,
    ROBOHASH,
    BLANK
}