package mbk.io.homework2.utils

import mbk.io.homework2.R


enum class CharacterStatus(val drawableResource: Int) {
    ALIVE(R.drawable.circle_green),
    DEAD(R.drawable.circle_red),
    UNKNOWN(R.drawable.circle_white)
}