import androidx.compose.ui.graphics.Color

enum class UIColours(colour: Color, colourName: String) {
    PRIMARY_1(Color.hsl(30f, 1f, .85f), "Soft Peach"),
    PRIMARY_2(Color.hsl(340f, .6f, .85f), "Gentle Blush"),
    PRIMARY_3(Color.hsl(45f, .7f, .7f), "Soft Mustard"),
    SECONDARY_3(Color.hsl(30f, .2f, .6f), "Warm Taupe"),
    SECONDARY_1(Color.hsl(42f, .5f, .8f), "Warm Sand"),
    SECONDARY_2(Color.hsl(40f, .5f, .95f), "Creamy White"),
    TERTIARY_1(Color.hsl(16f, .7f, .7f), "Muted Coral"),
    TERTIARY_2(Color.hsl(18f, .4f, .7f), "Muted Clay"),

    deepBrown(Color.hsl(30f, 0.35f, 0.20f),"Dark Brown"),     // Dark Brown
    darkChocolate(Color.hsl(30f, 0.45f, 0.30f),"Dark Chocolate Brown"), // Dark Chocolate Brown
    mocha(Color.hsl(30f, 0.35f, 0.40f),"Mocha Brown"),         // Mocha Brown
    warmTaupe(Color.hsl(30f, 0.25f, 0.50f),"Warm Taupe 2"),     // Warm Taupe
    latte(Color.hsl(30f, 0.30f, 0.55f),"Latte Beige"),         // Latte Beige
    softBeige(Color.hsl(30f, 0.25f, 0.65f),"Soft Beige"),     // Soft Beige
    creamyWhite(Color.hsl(30f, 0.20f, 0.75f),"Creamy White 2"),   // Creamy White
    ivory(Color.hsl(30f, 0.15f, 0.85f),"Ivory"),         // Ivory
    ;

    val colour = colour
    val colourName = colourName
}