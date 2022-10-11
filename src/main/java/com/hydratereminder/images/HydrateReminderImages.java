/*
 * Copyright (c) 2021, jmakhack <https://github.com/jmakhack>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.hydratereminder.images;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.hydratereminder.images.ImageCategories.*;
import static net.runelite.api.ItemID.*;

/**
 * <p>Different background images that can be swapped between for
 * the hydrate reminder overlay timer display
 * </p>
 * @author jmakhack
 */
@Getter
@AllArgsConstructor
public enum HydrateReminderImages
{
    // WATER
    BLESSED_WATER_IMAGE("Blessed Water", BLESSED_WATER, WATER, WATER),
    BOTTLED_WATER_IMAGE("Bottled Water", BOTTLED_WATER, WATER),
    BOWL_OF_BLUE_WATER_IMAGE("Bowl of Blue Water", BOWL_OF_BLUE_WATER, WATER),
    BOWL_OF_HOT_WATER_IMAGE("Bowl of Hot Water", BOWL_OF_HOT_WATER, WATER),
    BOWL_OF_RED_WATER_IMAGE("Bowl of Red Water", BOWL_OF_RED_WATER, WATER),
    BOWL_OF_WATER_IMAGE("Bowl of Water", BOWL_OF_WATER, WATER),
    BUCKET_OF_WATER_COOKOUT_IMAGE("Bucket of Water (Cookout)", BUCKET_OF_WATER_COOKOUT, WATER),
    BUCKET_OF_WATER_IMAGE("Bucket of Water", BUCKET_OF_WATER, WATER),
    CIRCLET_OF_WATER_IMAGE("Circlet of Water", CIRCLET_OF_WATER, WATER),
    CUP_OF_HOT_WATER_IMAGE("Cup of Hot Water", CUP_OF_HOT_WATER, WATER),
    CUP_OF_WATER_IMAGE("Cup of Water", CUP_OF_WATER, WATER),
    FULL_BUCKET_IMAGE("Full Bucket", FULL_BUCKET, WATER),
    FULL_JUG_IMAGE("Full Jug", FULL_JUG, WATER),
    GOLDEN_BOWL_IMAGE("Golden Bowl", GOLDEN_BOWL_724, WATER),
    GOLD_SINK_IMAGE("Gold Sink", GOLD_SINK, WATER),
    HOLY_WATER_IMAGE("Holy Water", HOLY_WATER, WATER),
    JUG_OF_WATER_IMAGE("Jug of Water", JUG_OF_WATER, WATER),
    LARGE_FOUNTAIN_IMAGE("Large Fountain", LARGE_FOUNTAIN, WATER),
    MURKY_WATER_IMAGE("Murky Water", MURKY_WATER, WATER),
    NETTLEWATER_IMAGE("Nettle-water", NETTLEWATER, WATER),
    POND_IMAGE("Pond", POND, WATER),
    PORTAL_TALISMAN_WATER_IMAGE("Portal Talisman (Water)", PORTAL_TALISMAN_WATER, WATER),
    POSH_FOUNTAIN_IMAGE("Posh Fountain", POSH_FOUNTAIN, WATER),
    SINK_IMAGE("Sink", SINK, WATER),
    SMALL_FOUNTAIN_IMAGE("Small Fountain", SMALL_FOUNTAIN, WATER),
    TOME_OF_WATER_IMAGE("Tome of Water", TOME_OF_WATER, WATER),
    VASE_OF_WATER_IMAGE("Vase of Water", VASE_OF_WATER, WATER),
    VIAL_OF_SORROW_IMAGE("Vial of Sorrow", VIAL_OF_SORROW, WATER),
    VIAL_OF_TEARS_IMAGE("Vial of Tears", VIAL_OF_TEARS_FULL, WATER),
    VIAL_OF_WATER_IMAGE("Vial of Water", VIAL_OF_WATER, WATER),
    WAKING_SLEEP_VIAL_IMAGE("Waking Sleep Vial", WAKING_SLEEP_VIAL, WATER),
    WATERFILLED_GOURD_VIAL_IMAGE("Water-filled Gourd Vial", WATERFILLED_GOURD_VIAL, WATER),
    WATERFILLED_VIAL_IMAGE("Water-filled Vial", WATERFILLED_VIAL, WATER),
    WATERSKIN_IMAGE("Waterskin", WATERSKIN4, WATER),
    WATER_CONTAINER_IMAGE("Water Container", WATER_CONTAINER, WATER),
    WATER_ORB_IMAGE("Water Orb", WATER_ORB, WATER),
    WATER_RUNE_IMAGE("Water Rune", WATER_RUNE, WATER),
    WATER_TALISMAN_IMAGE("Water Talisman", WATER_TALISMAN, WATER),
    WATER_TIARA_IMAGE("Water Tiara", WATER_TIARA, WATER),

    // TEA
    CLAY_CUP_OF_TEA_IMAGE("Clay Cup of Tea", CUP_OF_TEA_7730, TEA),
    CUP_OF_GREEN_TEA_IMAGE("Cup of Green Tea", CUP_OF_TEA_4242, TEA),
    CUP_OF_NETTLE_TEA_IMAGE("Cup of Nettle Tea", CUP_OF_TEA_4242, TEA),
    CUP_OF_TEA_IMAGE("Cup of Tea", CUP_OF_TEA, TEA),
    CUP_OF_WHITE_TEA_IMAGE("Cup of White Tea", CUP_OF_TEA_4243, TEA),
    GOLDTRIMMED_CUP_OF_TEA_IMAGE("Gold-trimmed Cup of Tea", CUP_OF_TEA_7736, TEA),
    GOLDTRIMMED_PORCELAIN_TEAPOT("Gold-trimmed Teapot", TEAPOT_7726, TEA),
    GOLD_TEAPOT_IMAGE("Golden Teapot", POT_OF_TEA_4_7716, TEA),
    GUTHIX_REST_IMAGE("Guthix Rest", GUTHIX_REST4, TEA),
    HERB_TEA_MIX_IMAGE("Herb Tea Mix", HERB_TEA_MIX, TEA),
    NETTLE_TEA_IMAGE("Nettle Tea", NETTLE_TEA, TEA),
    PORCELAIN_CUP_OF_GREEN_TEA_IMAGE("Porcelain Cup of Green Tea", CUP_OF_TEA_4245, TEA),
    PORCELAIN_CUP_OF_TEA_IMAGE("Porcelain Cup of Tea", CUP_OF_TEA_7733, TEA),
    PORCELAIN_CUP_OF_WHITE_TEA_IMAGE("Porcelain Cup of White Tea", CUP_OF_TEA_4246, TEA),
    POT_OF_TEA_IMAGE("Pot of Tea", POT_OF_TEA_4, TEA),
    RUINED_HERB_TEA_IMAGE("Ruined Herb Tea", RUINED_HERB_TEA, TEA),
    STRONG_CUP_OF_TEA_IMAGE("Strong Cup of Tea", STRONG_CUP_OF_TEA, TEA),
    TEAPOT_IMAGE("Clay Teapot", TEAPOT, TEA),
    TEA_FLASK_IMAGE("Tea Flask", TEA_FLASK, TEA),
    TEA_LEAVES_IMAGE("Tea Leaves", TEA_LEAVES, TEA),
    WHITE_PORCELAIN_TEAPOT_IMAGE("Porcelain Teapot", TEAPOT_7714, TEA),

    // ALCOHOL
    AHABS_BEER_IMAGE("Ahab's Beer", AHABS_BEER, ALCOHOL),
    ASGARNIAN_ALE_IMAGE("Asgarnian Ale", ASGARNIAN_ALE, ALCOHOL),
    ASGOLDIAN_ALE_IMAGE("Asgoldian Ale", ASGOLDIAN_ALE, ALCOHOL),
    BEER_BARREL_IMAGE("Beer Barrel", BEER_BARREL, ALCOHOL),
    BEER_IMAGE("Beer", BEER, ALCOHOL),
    BEER_TANKARD_IMAGE("Beer Tankard", BEER_TANKARD, ALCOHOL),
    BLOOD_PINT_IMAGE("Blood Pint", BLOOD_PINT, ALCOHOL),
    BLURBERRY_SPECIAL_IMAGE("Blurberry Special", BLURBERRY_SPECIAL, ALCOHOL),
    BOTTLE_OF_WINE_IMAGE("Bottle of Wine", BOTTLE_OF_WINE, ALCOHOL),
    BRAINDEATH_RUM_IMAGE("Braindeath 'Rum'", BRAINDEATH_RUM, ALCOHOL),
    BRANDY_IMAGE("Brandy", BRANDY, ALCOHOL),
    CHEFS_DELIGHT_IMAGE("Chef's Delight", CHEFS_DELIGHT, ALCOHOL),
    CHOC_SATURDAY_IMAGE("Choc Saturday", CHOC_SATURDAY, ALCOHOL),
    CIDER_IMAGE("Cider", CIDER_7752, ALCOHOL),
    COCKTAIL_SHAKER_IMAGE("Cocktail Shaker", COCKTAIL_SHAKER, ALCOHOL),
    DIRTY_BLAST_IMAGE("Dirty Blast", DIRTY_BLAST, ALCOHOL),
    DRAGON_BITTER_IMAGE("Dragon Bitter", DRAGON_BITTER, ALCOHOL),
    DRUNK_DRAGON_IMAGE("Drunk Dragon", DRUNK_DRAGON, ALCOHOL),
    DWARVEN_STOUT_IMAGE("Dwarven Stout", DWARVEN_STOUT, ALCOHOL),
    FESTIVE_MULLED_WINE_IMAGE("Festive Mulled Wine", FESTIVE_MULLED_WINE, ALCOHOL),
    FESTIVE_WHITE_WINE_IMAGE("Festive White Wine", FESTIVE_WHITE_WINE, ALCOHOL),
    FRUIT_BLAST_IMAGE("Fruit Blast", FRUIT_BLAST, ALCOHOL),
    GIN_IMAGE("Gin", GIN, ALCOHOL),
    GREENMANS_ALE_IMAGE("Greenmans Ale", GREENMANS_ALE, ALCOHOL),
    GROG_IMAGE("Grog", GROG, ALCOHOL),
    HAUNTED_WINE_BOTTLE_IMAGE("Haunted Wine Bottle", HAUNTED_WINE_BOTTLE, ALCOHOL),
    JUG_OF_WINE_IMAGE("Jug of Wine", JUG_OF_WINE, ALCOHOL),
    KEG_OF_BEER_IMAGE("Keg of Beer", KEG_OF_BEER, ALCOHOL),
    KELDA_STOUT_IMAGE("Kelda Stout", KELDA_STOUT, ALCOHOL),
    KHALI_BREW_IMAGE("Khali Brew", KHALI_BREW, ALCOHOL),
    KOVACS_GROG_IMAGE("Kovac's Grog", KOVACS_GROG, ALCOHOL),
    MOONLIGHT_MEAD_IMAGE("Moonlight Mead", MOONLIGHT_MEAD, ALCOHOL),
    ODD_COCKTAIL_IMAGE("Odd Cocktail", ODD_COCKTAIL, ALCOHOL),
    PINEAPPLE_PUNCH_IMAGE("Pineapple Punch", PINEAPPLE_PUNCH, ALCOHOL),
    RUM_IMAGE("Rum", RUM, ALCOHOL),
    SHORT_GREEN_GUY_IMAGE("Short Green Guy", SHORT_GREEN_GUY, ALCOHOL),
    SLAYERS_RESPITE_IMAGE("Slayer's Respite", SLAYERS_RESPITE, ALCOHOL),
    UNFINISHED_COCKTAIL_IMAGE("Unfinished Cocktail", UNFINISHED_COCKTAIL, ALCOHOL),
    VODKA_IMAGE("Vodka", VODKA, ALCOHOL),
    WHISKY_IMAGE("Whisky", WHISKY, ALCOHOL),
    WINE_OF_ZAMORAK_IMAGE("Wine of Zamorak", WINE_OF_ZAMORAK_23489, ALCOHOL),
    WIZARDS_MIND_BOMB_IMAGE("Wizards Mind Bomb", WIZARDS_MIND_BOMB, ALCOHOL),
    WIZARD_BLIZZARD_IMAGE("Wizard Blizzard", WIZARD_BLIZZARD, ALCOHOL),
    ZAMORAKS_UNFERMENTED_WINE_IMAGE("Zamorak's Unfermented Wine", ZAMORAKS_UNFERMENTED_WINE, ALCOHOL),

    // DRINK
    AUTUMN_SQIRKJUICE_IMAGE("Autumn Sq'irkjuice", AUTUMN_SQIRKJUICE, DRINK),
    BIG_BUCKET_OF_CAMEL_MILK_IMAGE("Big Bucket of Camel Milk", BIG_BUCKET_OF_CAMEL_MILK, DRINK),
    BIG_BUCKET_OF_FROZEN_CAMEL_MILK_IMAGE("Big Bucket of Frozen Camel Milk", BIG_BUCKET_OF_FROZEN_CAMEL_MILK, DRINK),
    BOTTLE_OF_TONIC_IMAGE("Bottle of 'Tonic'", BOTTLE_OF_TONIC, DRINK),
    BUCKET_OF_MILK_IMAGE("Bucket of Milk", BUCKET_OF_MILK, DRINK),
    CHOCOLATEY_MILK_IMAGE("Chocolatey Milk", CHOCOLATEY_MILK, DRINK),
    COCONUT_MILK_IMAGE("Coconut Milk", COCONUT_MILK, DRINK),
    ENDARKENED_JUICE_IMAGE("Endarkened Juice", ENDARKENED_JUICE, DRINK),
    MILKY_MIXTURE_IMAGE("Milky Mixture", MILKY_MIXTURE, DRINK),
    REDBERRY_JUICE_IMAGE("Redberry Juice", REDBERRY_JUICE, DRINK),
    SPRING_SQIRKJUICE_IMAGE("Spring Sq'irkjuice", SPRING_SQIRKJUICE, DRINK),
    SUMMER_SQIRKJUICE_IMAGE("Summer Sq'irkjuice", SUMMER_SQIRKJUICE, DRINK),
    WINTER_SQIRKJUICE_IMAGE("Winter Sq'irkjuice", WINTER_SQIRKJUICE, DRINK),

    // DRINK CONTAINERS
    COOLER_IMAGE("Cooler", COOLER, DRINKCONTAINERS),
    FULL_KETTLE_IMAGE("Full Kettle", FULL_KETTLE, DRINKCONTAINERS),
    HOT_KETTLE_IMAGE("Hot Kettle", HOT_KETTLE, DRINKCONTAINERS),
    ICE_COOLER_IMAGE("Ice Cooler", ICE_COOLER, DRINKCONTAINERS),
    JEREDS_EMPTY_WINE_BOTTLE_IMAGE("Jered's Empty Wine Bottle", JEREDS_EMPTY_WINE_BOTTLE, DRINKCONTAINERS),
    KEG_IMAGE("Keg", KEG, DRINKCONTAINERS),
    KETTLE_IMAGE("Kettle", KETTLE, DRINKCONTAINERS),
    SILVER_BOTTLE_IMAGE("Silver Bottle", SILVER_BOTTLE, DRINKCONTAINERS),
    SILVER_CUP_IMAGE("Silver Cup", SILVER_CUP, DRINKCONTAINERS),
    TANKARD_IMAGE("Tankard", TANKARD, DRINKCONTAINERS),

    // FOOD
    ANCHOVY_PASTE_IMAGE("Anchovy Paste", ANCHOVY_PASTE, FOOD),
    BANANA_STEW_IMAGE("Banana Stew", BANANA_STEW, FOOD),
    BRULEE_IMAGE("Brulee", BRULEE, FOOD),
    BURNT_CURRY_IMAGE("Burnt Curry", BURNT_CURRY, FOOD),
    BURNT_STEW_IMAGE("Burnt Stew", BURNT_STEW, FOOD),
    COOKING_POT_IMAGE("Cooking Pot", COOKING_POT, FOOD),
    CURRY_IMAGE("Curry", CURRY, FOOD),
    FESTIVE_POT_IMAGE("Festive Pot", FESTIVE_POT, FOOD),
    GOBLIN_STEW_IMAGE("Goblin Stew", GOBLIN_STEW, FOOD),
    INCOMPLETE_STEW_IMAGE("Incomplete Stew", INCOMPLETE_STEW, FOOD),
    JUG_OF_VINEGAR_IMAGE("Jug of Vinegar", JUG_OF_VINEGAR, FOOD),
    LIQUID_HONEY_IMAGE("Liquid Honey", LIQUID_HONEY, FOOD),
    OLIVE_OIL_IMAGE("Olive Oil", OLIVE_OIL4, FOOD),
    POT_OF_CREAM_IMAGE("Pot of Cream", POT_OF_CREAM, FOOD),
    POT_OF_VINEGAR_IMAGE("Pot of Vinegar", POT_OF_VINEGAR, FOOD),
    RED_HOT_SAUCE_IMAGE("Red Hot Sauce", RED_HOT_SAUCE, FOOD),
    SERVERY_STEW_IMAGE("Servery Stew", SERVERY_STEW, FOOD),
    SPECIAL_HOT_SAUCE_IMAGE("Special Hot Sauce", SPECIAL_HOT_SAUCE, FOOD),
    SPICY_SAUCE_IMAGE("Spicy Sauce", SPICY_SAUCE, FOOD),
    SPICY_STEW_IMAGE("Spicy Stew", SPICY_STEW, FOOD),
    STEW_IMAGE("Stew", STEW, FOOD),
    UNCOOKED_CURRY_IMAGE("Uncooked Curry", UNCOOKED_CURRY, FOOD),
    UNCOOKED_STEW_IMAGE("Uncooked Stew", UNCOOKED_STEW, FOOD),
    WATERMELON_SLICE_IMAGE("Watermelon Slice", WATERMELON_SLICE, FOOD),

    // POTION
    AGILITY_MIX_IMAGE("Agility Mix", AGILITY_MIX2, POTIONCATEGORY),
    AGILITY_POTION_IMAGE("Agility Potion", AGILITY_POTION4, POTIONCATEGORY),
    ANCIENT_BREW_IMAGE("Ancient Brew", ANCIENT_BREW4, POTIONCATEGORY),
    ANCIENT_MIX_IMAGE("Ancient Mix", ANCIENT_MIX2, POTIONCATEGORY),
    ANTIDOTE_IMAGE("Antidote", ANTIDOTE4, POTIONCATEGORY),
    ANTIDOTE_MIX_IMAGE("Antidote+ Mix", ANTIDOTE_MIX2, POTIONCATEGORY),
    ANTIFIRE_MIX_IMAGE("Antifire Mix", ANTIFIRE_MIX2, POTIONCATEGORY),
    ANTIFIRE_POTION_IMAGE("Antifire Potion", ANTIFIRE_POTION4, POTIONCATEGORY),
    ANTIPOISON_IMAGE("Antipoison", ANTIPOISON4, POTIONCATEGORY),
    ANTIPOISON_MIX_IMAGE("Antipoison Mix", ANTIPOISON_MIX2, POTIONCATEGORY),
    ANTIPOISON_SUPERMIX_IMAGE("Antipoison Supermix", ANTIPOISON_SUPERMIX2, POTIONCATEGORY),
    ANTIVENOM_IMAGE("Anti-venom", ANTIVENOM4, POTIONCATEGORY),
    ANTIVENOM_PLUS_IMAGE("Anti-venom+", ANTIVENOM4_12913, POTIONCATEGORY),
    ATTACK_MIX_IMAGE("Attack Mix", ATTACK_MIX2, POTIONCATEGORY),
    ATTACK_POTION_IMAGE("Attack Potion", ATTACK_POTION4, POTIONCATEGORY),
    AVANTOE_POTION_IMAGE("Avantoe Potion", AVANTOE_POTION_UNF, POTIONCATEGORY),
    BASTION_POTION_IMAGE("Bastion Potion", BASTION_POTION4, POTIONCATEGORY),
    BATTLEMAGE_POTION_IMAGE("Battlemage Potion", BATTLEMAGE_POTION4, POTIONCATEGORY),
    BLIGHTED_SUPER_RESTORE_IMAGE("Blighted Super Restore", BLIGHTED_SUPER_RESTORE4, POTIONCATEGORY),
    BLOOD_POTION_IMAGE("Blood Potion", BLOOD_POTION, POTIONCATEGORY),
    BRAVERY_POTION_IMAGE("Bravery Potion", BRAVERY_POTION, POTIONCATEGORY),
    CADANTINE_BLOOD_POTION_IMAGE("Cadantine Blood Potion", CADANTINE_BLOOD_POTION_UNF, POTIONCATEGORY),
    CADANTINE_POTION_IMAGE("Cadantine Potion", CADANTINE_POTION_UNF, POTIONCATEGORY),
    CADAVA_POTION_IMAGE("Cadava Potion", CADAVA_POTION, POTIONCATEGORY),
    CASTLEWARS_BREW_IMAGE("Castlewars Brew", CASTLEWARS_BREW4, POTIONCATEGORY),
    CAT_ANTIPOISON_IMAGE("Cat Antipoison", CAT_ANTIPOISON, POTIONCATEGORY),
    COMBAT_MIX_IMAGE("Combat Mix", COMBAT_MIX2, POTIONCATEGORY),
    COMBAT_POTION_IMAGE("Combat Potion", COMBAT_POTION4_26150, POTIONCATEGORY),
    COMPOST_POTION_IMAGE("Compost Potion", COMPOST_POTION4, POTIONCATEGORY),
    DEFENCE_MIX_IMAGE("Defence Mix", DEFENCE_MIX2, POTIONCATEGORY),
    DEFENCE_POTION_IMAGE("Defence Potion", DEFENCE_POTION4, POTIONCATEGORY),
    DIVINE_BASTION_POTION_IMAGE("Divine Bastion Potion", DIVINE_BASTION_POTION4, POTIONCATEGORY),
    DIVINE_BATTLEMAGE_POTION_IMAGE("Divine Battlemage Potion", DIVINE_BATTLEMAGE_POTION4, POTIONCATEGORY),
    DIVINE_MAGIC_POTION_IMAGE("Divine Magic Potion", DIVINE_MAGIC_POTION4, POTIONCATEGORY),
    DIVINE_RANGING_POTION_IMAGE("Divine Ranging Potion", DIVINE_RANGING_POTION4, POTIONCATEGORY),
    DIVINE_SUPER_ATTACK_POTION_IMAGE("Divine Super Attack Potion", DIVINE_SUPER_ATTACK_POTION4, POTIONCATEGORY),
    DIVINE_SUPER_COMBAT_POTION_IMAGE("Divine Super Combat Potion", DIVINE_SUPER_COMBAT_POTION4, POTIONCATEGORY),
    DIVINE_SUPER_DEFENCE_POTION_IMAGE("Divine Super Defence Potion", DIVINE_SUPER_DEFENCE_POTION4, POTIONCATEGORY),
    DIVINE_SUPER_STRENGTH_POTION_IMAGE("Divine Super Strength Potion", DIVINE_SUPER_STRENGTH_POTION4, POTIONCATEGORY),
    DREAM_POTION_IMAGE("Dream Potion", DREAM_POTION, POTIONCATEGORY),
    DWARF_WEED_POTION_IMAGE("Dwarf Weed Potion", DWARF_WEED_POTION_UNF, POTIONCATEGORY),
    EGNIOL_POTION_IMAGE("Egniol Potion", EGNIOL_POTION_4, POTIONCATEGORY),
    ELDER_POTION_IMAGE("Elder Potion", ELDER_POTION_4, POTIONCATEGORY),
    ENERGY_MIX_IMAGE("Energy Mix", ENERGY_MIX2, POTIONCATEGORY),
    ENERGY_POTION_IMAGE("Energy Potion", ENERGY_POTION4, POTIONCATEGORY),
    EXPOSIVE_POTION_IMAGE("Explosive Potion", EXPLOSIVE_POTION, POTIONCATEGORY),
    EXTENDED_SUPER_ANTIFIRE_IMAGE("Extended Super Antifire", EXTENDED_SUPER_ANTIFIRE4, POTIONCATEGORY),
    FISHING_MIX_IMAGE("Fishing Mix", FISHING_MIX2, POTIONCATEGORY),
    FISHING_POTION_IMAGE("Fishing Potion", FISHING_POTION4, POTIONCATEGORY),
    FISH_VIAL_IMAGE("Fish Vial", FISH_VIAL, POTIONCATEGORY),
    GOBLIN_POTION_IMAGE("Goblin Potion", GOBLIN_POTION4, POTIONCATEGORY),
    GRYM_POTION_IMAGE("Grym Potion", GRYM_POTION_UNF, POTIONCATEGORY),
    GUAM_POTION_IMAGE("Guam Potion", GUAM_POTION_UNF, POTIONCATEGORY),
    HARRALANDER_POTION_IMAGE("Harralander Potion", HARRALANDER_POTION_UNF, POTIONCATEGORY),
    HEALING_VIAL_IMAGE("Healing Vial", HEALING_VIAL4, POTIONCATEGORY),
    HUNTING_MIX_IMAGE("Hunting Mix", HUNTING_MIX2, POTIONCATEGORY),
    INVERSION_POTION_IMAGE("Inversion Potion", INVERSION_POTION, POTIONCATEGORY),
    IRIT_POTION_IMAGE("Irit Potion", IRIT_POTION_UNF, POTIONCATEGORY),
    KODAI_POTION_IMAGE("Kodai Potion", KODAI_POTION_4, POTIONCATEGORY),
    KWUARM_POTION_IMAGE("Kwuarm Potion", KWUARM_POTION_UNF, POTIONCATEGORY),
    LANTADYME_POTION_IMAGE("Lantadyme Potion", LANTADYME_POTION_UNF, POTIONCATEGORY),
    MAGICAL_CLEANING_POTION_IMAGE("Magical Cleaning Potion", MAGICAL_CLEANING_POTION, POTIONCATEGORY),
    MAGIC_ESSENCE_MIX_IMAGE("Magic Essence Mix", MAGIC_ESSENCE_MIX2, POTIONCATEGORY),
    MAGIC_MIX_IMAGE("Magic Mix", MAGIC_MIX2, POTIONCATEGORY),
    MAGIC_OGRE_POTION_IMAGE("Magic Ogre Potion", MAGIC_OGRE_POTION, POTIONCATEGORY),
    MAGIC_POTION_IMAGE("Magic Potion", MAGIC_POTION4, POTIONCATEGORY),
    MARRENTILL_POTION_IMAGE("Marrentill Potion", MARRENTILL_POTION_UNF, POTIONCATEGORY),
    MIXTURE_STEP_ONE_IMAGE("Mixture - Step 1", MIXTURE__STEP_14, POTIONCATEGORY),
    MURKY_POTION_IMAGE("Murky Potion", MURKY_POTION, POTIONCATEGORY),
    OVERLOAD_IMAGE("Overload", OVERLOAD_4_20988, POTIONCATEGORY),
    PLANT_CURE_IMAGE("Plant Cure", PLANT_CURE_6468, POTIONCATEGORY),
    POTION_IMAGE("Potion", POTION, POTIONCATEGORY),
    POTION_OF_POWER_IMAGE("Potion of Power", POTION_OF_POWER4, POTIONCATEGORY),
    POTION_OF_SEALEGS_IMAGE("Potion of Sealegs", POTION_OF_SEALEGS, POTIONCATEGORY),
    PRAYER_ENHANCE_IMAGE("Prayer Enhance", PRAYER_ENHANCE_4, POTIONCATEGORY),
    PRAYER_MIX_IMAGE("Prayer Mix", PRAYER_MIX2, POTIONCATEGORY),
    PRAYER_POTION_IMAGE("Prayer Potion", PRAYER_POTION4, POTIONCATEGORY),
    RANARR_POTION_IMAGE("Ranarr Potion", RANARR_POTION_UNF, POTIONCATEGORY),
    RANGING_MIX_IMAGE("Ranging Mix", RANGING_MIX2, POTIONCATEGORY),
    RANGING_POTION_IMAGE("Ranging Potion", RANGING_POTION4, POTIONCATEGORY),
    REJUVENATION_POTION_IMAGE("Rejuvenation Potion", REJUVENATION_POTION_4, POTIONCATEGORY),
    RELICYMS_MIX_IMAGE("Relicyms Mix", RELICYMS_MIX2, POTIONCATEGORY),
    RESTORE_MIX_IMAGE("Restore Mix", RESTORE_MIX2, POTIONCATEGORY),
    RESTORE_POTION_IMAGE("Restore Potion", RESTORE_POTION4, POTIONCATEGORY),
    REVITALISATION_POTION_IMAGE("Revitalisation Potion", REVITALISATION_POTION_4, POTIONCATEGORY),
    SANFEW_SERUM_IMAGE("Sanfew Serum", SANFEW_SERUM4, POTIONCATEGORY),
    SARADOMIN_BREW_IMAGE("Saradomin Brew", SARADOMIN_BREW4_23575, POTIONCATEGORY),
    SHIELDING_POTION_IMAGE("Shielding Potion", SHIELDING_POTION, POTIONCATEGORY),
    SNAPDRAGON_POTION_IMAGE("Snapdragon Potion", SNAPDRAGON_POTION_UNF, POTIONCATEGORY),
    SPECTRAL_POTION_IMAGE("Spectral Potion", SPECTRAL_POTION, POTIONCATEGORY),
    STAMINA_MIX_IMAGE("Stamina Mix", STAMINA_MIX2, POTIONCATEGORY),
    STAMINA_POTION_IMAGE("Stamina Potion", STAMINA_POTION4, POTIONCATEGORY),
    STRANGE_POTION_IMAGE("Strange Potion", STRANGE_POTION, POTIONCATEGORY),
    STRENGTH_MIX_IMAGE("Strength Mix", STRENGTH_MIX2, POTIONCATEGORY),
    STRENGTH_POTION_IMAGE("Strength Potion", STRENGTH_POTION4, POTIONCATEGORY),
    SULPHUR_POTION_IMAGE("Sulphur Potion", SULPHUR_POTION, POTIONCATEGORY),
    SUPERATTACK_MIX_IMAGE("Superattack Mix", SUPERATTACK_MIX2, POTIONCATEGORY),
    SUPER_ANTIFIRE_MIX_IMAGE("Super Antifire Mix", SUPER_ANTIFIRE_MIX2, POTIONCATEGORY),
    SUPER_ANTIFIRE_POTION_IMAGE("Super Antifire Potion", SUPER_ANTIFIRE_POTION4, POTIONCATEGORY),
    SUPER_ANTIPOISON_IMAGE("Super Antipoison", SUPERANTIPOISON4, POTIONCATEGORY),
    SUPER_ATTACK_POTION_IMAGE("Super Attack Potion", SUPER_ATTACK4, POTIONCATEGORY),
    SUPER_COMBAT_POTION_IMAGE("Super Combat Potion", SUPER_COMBAT_POTION4, POTIONCATEGORY),
    SUPER_DEFENCE_MIX_IMAGE("Super Defence Mix", SUPER_DEF_MIX2, POTIONCATEGORY),
    SUPER_DEFENCE_POTION_IMAGE("Super Defence Potion", SUPER_DEFENCE4, POTIONCATEGORY),
    SUPER_ENERGY_MIX_IMAGE("Super Energy Mix", SUPER_ENERGY_MIX2, POTIONCATEGORY),
    SUPER_ENERGY_POTION_IMAGE("Super Energy Potion", SUPER_ENERGY4, POTIONCATEGORY),
    SUPER_RESTORE_IMAGE("Super Restore", SUPER_RESTORE4_23567, POTIONCATEGORY),
    SUPER_RESTORE_MIX_IMAGE("Super Restore Mix", SUPER_RESTORE_MIX2, POTIONCATEGORY),
    SUPER_RESTORE_POTION_IMAGE("Super Restore Potion", SUPER_RESTORE4, POTIONCATEGORY),
    SUPER_STRENGTH_MIX_IMAGE("Super Strength Mix", SUPER_STR_MIX2, POTIONCATEGORY),
    SUPER_STRENGTH_POTION_IMAGE("Super Strength Potion", SUPER_STRENGTH4, POTIONCATEGORY),
    TARROMIN_POTION_IMAGE("Tarromin Potion", TARROMIN_POTION_UNF, POTIONCATEGORY),
    TOADFLAX_POTION_IMAGE("Toadflax Potion", TOADFLAX_POTION_UNF, POTIONCATEGORY),
    TORSTOL_POTION_IMAGE("Torstol Potion", TORSTOL_POTION_UNF, POTIONCATEGORY),
    TRUTH_SERUM_IMAGE("Truth Serum", TRUTH_SERUM, POTIONCATEGORY),
    TWISTED_POTION_IMAGE("Twisted Potion", TWISTED_POTION_4, POTIONCATEGORY),
    VOICE_POTION_IMAGE("Voice Potion", VOICE_POTION, POTIONCATEGORY),
    XERICS_AID_IMAGE("Xeric's Aid", XERICS_AID_4, POTIONCATEGORY),
    ZAMORAK_BREW_IMAGE("Zamorak Brew", ZAMORAK_BREW4, POTIONCATEGORY),
    ZAMORAK_MIX_IMAGE("Zamorak Mix", ZAMORAK_MIX2, POTIONCATEGORY),

    // DYE
    ABYSSAL_BLUE_DYE_IMAGE("Abyssal Blue Dye", ABYSSAL_BLUE_DYE, DYE),
    ABYSSAL_GREEN_DYE_IMAGE("Abyssal Green Dye", ABYSSAL_GREEN_DYE, DYE),
    ABYSSAL_RED_DYE_IMAGE("Abyssal Red Dye", ABYSSAL_RED_DYE, DYE),
    BLACK_MUSHROOM_INK_IMAGE("Black Mushroom Ink", BLACK_MUSHROOM, DYE),
    BLUE_DYE_IMAGE("Blue Dye", BLUE_DYE, DYE),
    GREEN_DYE_IMAGE("Green Dye", GREEN_DYE, DYE),
    ORANGE_DYE_IMAGE("Orange Dye", ORANGE_DYE, DYE),
    PINK_DYE_IMAGE("Pink Dye", PINK_DYE, DYE),
    PURPLE_DYE_IMAGE("Purple Dye", PURPLE_DYE, DYE),
    RED_DYE_IMAGE("Red Dye", RED_DYE, DYE),
    YELLOW_DYE_IMAGE("Yellow Dye", YELLOW_DYE, DYE),

    // CHEMICAL
    ACETIC_ACID_IMAGE("Acetic Acid", ACETIC_ACID, CHEMICAL),
    BLUE_LIQUID_IMAGE("Blue Liquid", BLUE_LIQUID, CHEMICAL),
    CHEMICAL_COMPOUND_IMAGE("Chemical Compound", CHEMICAL_COMPOUND, CHEMICAL),
    CLEAR_LIQUID_IMAGE("Clear Liquid", CLEAR_LIQUID, CHEMICAL),
    CUPRIC_ORE_POWDER_IMAGE("Cupric Ore Powder", CUPRIC_ORE_POWDER, CHEMICAL),
    CUPRIC_SULFATE_IMAGE("Cupric Sulfate", CUPRIC_SULFATE, CHEMICAL),
    GYPSUM_IMAGE("Gypsum", GYPSUM, CHEMICAL),
    HAGS_POISON_IMAGE("Hags Poison", HAGS_POISON, CHEMICAL),
    INSECT_REPELLENT_IMAGE("Insect Repellent", INSECT_REPELLENT, CHEMICAL),
    JAR_OF_CHEMICALS_IMAGE("Jar of Chemicals", JAR_OF_CHEMICALS, CHEMICAL),
    MIXED_CHEMICALS_IMAGE("Mixed Chemicals", MIXED_CHEMICALS, CHEMICAL),
    MIXTURE_IMAGE("Mixture", _MIXTURE, CHEMICAL),
    NAPHTHA_MIX_IMAGE("Naphtha Mix", NAPHTHA_MIX, CHEMICAL),
    NITROGLYCERIN_IMAGE("Nitroglycerin", NITROGLYCERIN, CHEMICAL),
    NITROUS_OXIDE_IMAGE("Nitrous Oxide", NITROUS_OXIDE, CHEMICAL),
    POISON_CHALICE_IMAGE("Poison Chalice", POISON_CHALICE, CHEMICAL),
    POISON_IMAGE("Poison", POISON, CHEMICAL),
    RAT_POISON_IMAGE("Rat Poison", RAT_POISON, CHEMICAL),
    SERUM_IMAGE("Serum", SERUM_208_4, CHEMICAL),
    SODIUM_CHLORIDE_IMAGE("Sodium Chloride", SODIUM_CHLORIDE, CHEMICAL),
    SULPHURIC_BROLINE_IMAGE("Sulphuric Broline", SULPHURIC_BROLINE, CHEMICAL),
    TIN_ORE_POWDER_IMAGE("Tin Ore Powder", TIN_ORE_POWDER, CHEMICAL),
    TOXIC_NAPHTHA_IMAGE("Toxic Naphtha", TOXIC_NAPHTHA, CHEMICAL),
    VIAL_OF_LIQUID_IMAGE("Vial of Liquid", VIAL_OF_LIQUID, CHEMICAL),
    WEAPON_POISON_IMAGE("Weapon Poison", WEAPON_POISON_5940, CHEMICAL),

    // OTHER
    BAILING_BUCKET_IMAGE("Bailing Bucket", BAILING_BUCKET_585, OTHER),
    BINDING_FLUID_IMAGE("Binding Fluid", BINDING_FLUID, OTHER),
    BLAMISH_OIL_IMAGE("Blamish Oil", BLAMISH_OIL, OTHER),
    BLAMISH_SNAIL_SLIME_IMAGE("Blamish Snail Slime", BLAMISH_SNAIL_SLIME, OTHER),
    BLESSED_POT_IMAGE("Blessed Pot", BLESSED_POT_4667, OTHER),
    BONE_IN_VINEGAR_IMAGE("Bone in Vinegar", BONE_IN_VINEGAR_7816, OTHER),
    BOTTLED_DRAGONBREATH_IMAGE("Bottled Dragonbreath", BOTTLED_DRAGONBREATH, OTHER),
    BOTTOMLESS_COMPOST_BUCKET_IMAGE("Bottomless Compost Bucket", BOTTOMLESS_COMPOST_BUCKET, OTHER),
    BROKEN_CAULDRON_IMAGE("Broken Cauldron", BROKEN_CAULDRON, OTHER),
    BUCKET_OF_RUBBLE_IMAGE("Bucket of Rubble", BUCKET_OF_RUBBLE_7626, OTHER),
    BUCKET_OF_SAND_IMAGE("Bucket of Sand", BUCKET_OF_SAND, OTHER),
    BUCKET_OF_SLIME_IMAGE("Bucket of Slime", BUCKET_OF_SLIME, OTHER),
    BUCKET_OF_WAX_IMAGE("Bucket of Wax", BUCKET_OF_WAX, OTHER),
    FANCY_REJUVENATION_POOL_IMAGE("Fancy Rejuvenation Pool", FANCY_REJUVENATION_POOL, OTHER),
    FROZEN_BUCKET_IMAGE("Frozen Bucket", FROZEN_BUCKET, OTHER),
    FROZEN_JUG_IMAGE("Frozen Jug", FROZEN_JUG, OTHER),
    FROZEN_WHIP_MIX_IMAGE("Frozen Whip Mix", FROZEN_WHIP_MIX, OTHER),
    GOAT_DUNG_IMAGE("Goat Dung", GOAT_DUNG, OTHER),
    GOLDEN_POT_IMAGE("Golden Pot", GOLDEN_POT, OTHER),
    HOLY_ELIXIR_IMAGE("Holy Elixir", HOLY_ELIXIR, OTHER),
    JAR_OF_DARKNESS_IMAGE("Jar of Darkness", JAR_OF_DARKNESS, OTHER),
    JAR_OF_DECAY_IMAGE("Jar of Decay", JAR_OF_DECAY, OTHER),
    JAR_OF_DIRT_IMAGE("Jar of Dirt", JAR_OF_DIRT, OTHER),
    JAR_OF_DREAMS_IMAGE("Jar of Dreams", JAR_OF_DREAMS, OTHER),
    JAR_OF_SAND_IMAGE("Jar of Sand", JAR_OF_SAND, OTHER),
    JAR_OF_SMOKE_IMAGE("Jar of Smoke", JAR_OF_SMOKE, OTHER),
    JAR_OF_SOULS_IMAGE("Jar of Souls", JAR_OF_SOULS, OTHER),
    JAR_OF_SPIRITS_IMAGE("Jar of Spirits", JAR_OF_SPIRITS, OTHER),
    LILY_OF_THE_ELID_IMAGE("Lily of the Elid", LILY_OF_THE_ELID, OTHER),
    MAGIC_GLUE_IMAGE("Magic Glue", MAGIC_GLUE, OTHER),
    OIL_CAN_IMAGE("Oil Can", OIL_CAN, OTHER),
    ORNATE_REJUVENATION_POOL_IMAGE("Ornate Rejuvenation Pool", ORNATE_REJUVENATION_POOL, OTHER),
    PUDDLE_OF_SLIME_IMAGE("Puddle of Slime", PUDDLE_OF_SLIME, OTHER),
    PUNGENT_POT_IMAGE("Pungent Pot", PUNGENT_POT, OTHER),
    REJUVENATION_POOL_IMAGE("Rejuvenation Pool", REJUVENATION_POOL, OTHER),
    RESTORATION_POOL_IMAGE("Restoration Pool", RESTORATION_POOL, OTHER),
    REVITALISATION_POOL_IMAGE("Revitalisation Pool", REVITALISATION_POOL, OTHER),
    SACRED_OIL_IMAGE("Sacred Oil", SACRED_OIL4, OTHER),
    SILVER_POT_IMAGE("Silver Pot", SILVER_POT, OTHER),
    SLOP_OF_COMPROMISE_IMAGE("Slop of Compromise", SLOP_OF_COMPROMISE, OTHER),
    SMOULDERING_POT_IMAGE("Smouldering Pot", SMOULDERING_POT, OTHER),
    SNOW_IMAGE("Snow", SNOW, OTHER),
    UNKNOWN_FLUID_FIVE_IMAGE("Unknown Fluid 5", UNKNOWN_FLUID_5, OTHER),
    UNKNOWN_FLUID_FOUR_IMAGE("Unknown Fluid 4", UNKNOWN_FLUID_4, OTHER),
    UNKNOWN_FLUID_ONE_IMAGE("Unknown Fluid 1", UNKNOWN_FLUID_1, OTHER),
    UNKNOWN_FLUID_THREE_IMAGE("Unknown Fluid 3", UNKNOWN_FLUID_3, OTHER),
    UNKNOWN_FLUID_TWO_IMAGE("Unknown Fluid 2", UNKNOWN_FLUID_2, OTHER),
    VIAL_OF_BLOOD_IMAGE("Vial of Blood", VIAL_OF_BLOOD, OTHER),
    VOLCANIC_WHIP_MIX_IMAGE("Volcanic Whip Mix", VOLCANIC_WHIP_MIX, OTHER),
    WASHING_BOWL_IMAGE("Washing Bowl", WASHING_BOWL, OTHER),
    WAX_IMAGE("Wax", WAX, OTHER),
    WEIRD_GLOOP_IMAGE("Weird Gloop", WEIRD_GLOOP, OTHER);

    /**
     * Description of the image
     */
    private final String imageDescription;

    /**
     * ID of the image
     */
    private final int imageID;

    /**
     * Category of the image
     */
    private final ImageCategories imageCategory;

    /**
     * <p>Get the description of the image
     * </p>
     * @return image description
     * @since 1.2.0
     */
    @Override
    public String toString()
    {
        return getImageDescription();
    }

    /**
     * <p>Get the ID of the image
     * </p>
     * @return image ID
     * @since 1.2.0
     */
    public int getID()
    {
        return getImageID();
    }

    /**
     * <p>Get the category of the image
     * </p>
     * @return image category
     */
    public ImageCategories getCategory() { return getImageCategory() }
}
