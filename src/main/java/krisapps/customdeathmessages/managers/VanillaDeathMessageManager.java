package krisapps.customdeathmessages.managers;

import krisapps.customdeathmessages.CustomDeathMessages;
import krisapps.customdeathmessages.logging.Logger;
import krisapps.customdeathmessages.logging.LoggingLevel;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

import javax.annotation.Nullable;
import java.util.Locale;
import java.util.Objects;

public class VanillaDeathMessageManager {

    CustomDeathMessages main;
    Logger log;
    public VanillaDeathMessageManager(CustomDeathMessages main){
        this.main = main;
        log = new Logger(main);
    }

    // Follows a list of hardcoded messages to identify death messages.
    String accident_ladder = "fell off a ladder";
    String accident_vines = "fell off some vines";
    String accident_weepingvines = "fell off some weeping vines";
    String accident_twistingvines = "fell off some twisting vines";
    String accident_scaffolding = "fell off some scaffolding";
    String accident_otherclimbable = "fell while climbing";
    String accident_generic = "fell from a high place";

    String killer = "was doomed to fall";
    String assisted_death = "was doomed to fall by";
    String assisted_death_with_item_1 = "was doomed to fall by";
    String assisted_death_with_item_2 = "by";

    String finish = "fell too far and was finished by";
    String finish_item_1 = "fell too far and was finished by";
    String finish_item_2 = "using";

    String lightning = "was struck by lightning";
    String lightning_pvp = "was struck by lightning whilst fighting";

    String fire = "went up in flames";
    String fire_pvp = "walked into fire whilst fighting";

    String onFire = "burned to death";
    String onFire_pvp = "burnt to a crisp whilst fighting";

    String lava = "tried to swim in lava";
    String lava_pvp = "tried to swim in lava to escape";

    String hotfloor = "discovered the floor was lava";
    String hotfloor_assisted = "walked into danger zone due to";

    String wall_suff = "suffocated in a wall";
    String wall_suff_assisted = "suffocated in a wall whilst fighting";

    String squished = "was squished too much";
    String squished_assisted = "was squashed by";

    String drown = "drowned";
    String drown_assisted = "drowned whilst trying to escape";

    String dry = "died from dehydration";
    String dry_assisted = "died from dehydration whilst trying to escape";

    String starve = "starved to death";
    String starve_pvp = "starved to death whilst fighting";

    String cactus = "was pricked to death";
    String cactus_pvp = "walked into a cactus whilst trying to escape";

    String generic = "died";
    String generic_pvp = "died because of";

    String explosion = "blew up";
    String assisted_explosion = "was blown up by";

    String assisted_item_explosion_1 = "was blown up by";
    String assisted_item_explosion_2 = "using";

    String magic = "was killed by magic";
    String magic_pvp = "was killed by magic whilst trying to escape";
    String magic_a_lot = "was killed by even more magic";

    String message_too_long = "Actually, message was too long to deliver fully. Sorry! Here's stripped version:";

    String wither = "withered away";
    String wither_pvp = "withered away whilst fighting";
    String wither_skull = "was shot by a skull from";

    String anvil = "was squashed by a falling anvil";
    String anvil_pvp = "was squashed by a falling anvil whilst fighting";

    String fallingBlock = "was squashed by a falling block";
    String fallingBlock_pvp = "was squashed by a falling block whilst fighting";

    String stalagmite = "was impaled on a stalagmite";
    String stalagmite_pvp = "was impaled on a stalagmite whilst fighting";

    String fallingStalactite = "was skewered by a falling stalactite";
    String fallingStalactite_pvp = "was skewered by a falling stalactite whilst fighting";

    String killedby_mob = "was slain by";
    String killedby_mob_item1 = "was slain by";
    String killedby_mob_item2 = "using";
    String killedby_player = "was slain by";
    String killedby_player_item1 = "was slain by";
    String killedby_player_item2 = "using";

    String arrow = "was shot by";
    String arrow_item = "using";

    String fireball = "was fireballed by";
    String fireball_item = "using";

    String thrown = "was pummeled by";
    String thrown_item = "using";

    String indirectMagic = "was killed by";
    String usingmagic = "using magic";
    String indirectMagic_item = "using";

    String thorns = "was killed trying to hurt";
    String thorns_hurt = "trying to hurt";
    String thorns_item = "was killed by";

    String trident = "was impaled by";
    String trident_item_using = "using";

    String fall = "hit the ground too hard";
    String fall_pvp = "hit the ground too hard whilst trying to escape";

    String outOfWorld = "fell out of world";
    String outOfWorld_pvp = "didn't want to live in the same world as";

    String dragonbreath = "was roasted in dragon breath";
    String dragonbreath_pvp = "was roassted in dragon breath by";

    String flyIntoWall = "experienced kinetic energy";
    String flyIntoWall_pvp = "experienced kinetic energy whilst trying to escape";

    String fireworks = "went off with a bang";
    String fireworks_pvp = "went off with a bang whilst fighting";
    String fireworks_item = "went off with a bang due to a firework fired from";

    String badRespawnPoint = "was killed by";
    String badRespawnPoint_link = "Intentional Game Design";

    String berrybush = "was poked to death by a sweet berry bush";
    String berrybush_pvp = "was poked to death by a sweet berry bush whilst trying to escape";

    String sting = "was stung to death";
    String sting_pvp = "was stung to death by";

    String freeze = "froze to death";
    String freeze_pvp = "was frozen to death by";








    public String handlePlayerDeath(String playerUUID, PlayerDeathEvent deathEvent){
        String deathMessage = "";
        Player player = Bukkit.getServer().getPlayer(playerUUID);
        String originalDeathMessage = deathEvent.getDeathMessage();
        String killer = "";
        if (deathEvent.getEntity().getKiller() != null) {
            killer = deathEvent.getEntity().getKiller().getDisplayName();
        }else{
            killer = "#NOKILLER#";
        }

        main.getLogger().info("Handling player death for " + player.getDisplayName());

        // death.fell

            if (originalDeathMessage.contains(accident_ladder)) {
                deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.fell.accident.ladder"), player.getDisplayName());
                return deathMessage;
            }

            if (originalDeathMessage.contains(accident_vines)) {
                deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.fell.accident.vines"), player.getDisplayName());
                return deathMessage;
            }

            if (originalDeathMessage.contains(accident_weepingvines)) {
                deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.fell.accident.weeping_vines"), player.getDisplayName());
                return deathMessage;
            }

            if (originalDeathMessage.contains(accident_twistingvines)) {
                deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.fell.accident.twisting_vines"), player.getDisplayName());
                return deathMessage;
            }

            if (originalDeathMessage.contains(accident_scaffolding)) {
                deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.fell.accident.scaffolding"), player.getDisplayName());
                return deathMessage;
            }

            if (originalDeathMessage.contains(accident_otherclimbable)) {
                deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.fell.accident.other_climbable"), player.getDisplayName());
                return deathMessage;
            }

            if (originalDeathMessage.contains(accident_generic)) {
                deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.fell.accident.generic"), player.getDisplayName());
                return deathMessage;
            }

            if (originalDeathMessage.contains(killer)) {
                deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.fell.killer"), player.getDisplayName());
                return deathMessage;
            }

            if (originalDeathMessage.contains(assisted_death)) {
                deathMessage = formatMessagePVP(getMessageFromFile("death.fell.assist"), player.getDisplayName(), killer);
                return deathMessage;
            }

            if (originalDeathMessage.contains(assisted_death_with_item_1) && originalDeathMessage.contains(assisted_death_with_item_2)) {
                deathMessage = formatMessagePVPItem(getMessageFromFile("death.fell.accident.weeping_vines"), player.getDisplayName(), killer, getKillerWeapon(deathEvent));
                return deathMessage;
            }

            if (originalDeathMessage.contains(finish)) {
                deathMessage = formatMessagePVP(getMessageFromFile("death.fell.finish"), player.getDisplayName(), killer);
                return deathMessage;
            }

            if (originalDeathMessage.contains(finish_item_1) && originalDeathMessage.contains(finish_item_2)) {
                deathMessage = formatMessagePVPItem(getMessageFromFile("death.fell.accident.finish_item"), player.getDisplayName(), killer, getKillerWeapon(deathEvent));
                return deathMessage;
            }

            // death.attack

        if (originalDeathMessage.contains(lightning)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.lightingBolt"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(lightning_pvp)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.lightningBolt_player"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(fire)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.inFire"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(fire_pvp)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.inFire_player"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(onFire)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.onFire"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(onFire_pvp)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.onFire_player"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(lava)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.lava"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(lava_pvp)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.lava_player"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(hotfloor)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.hotFloor"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(hotfloor_assisted)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.hotFloor_player"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(wall_suff)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.inWall"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(wall_suff_assisted)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.inWall_player"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(squished)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.cramming"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(squished_assisted)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.cramming_player"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(drown)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.drown"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(drown_assisted)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.drown_player"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(dry)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.dryout"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(dry_assisted)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.dryout_player"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(starve)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.starve"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(starve_pvp)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.starve_player"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(cactus)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.cactus"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(cactus_pvp)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.cactus_player"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(generic)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.generic"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(generic_pvp)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.generic_player"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(explosion)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.explosion"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(assisted_explosion)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.explosion_player"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(assisted_item_explosion_1) && originalDeathMessage.contains(assisted_item_explosion_2)){
            deathMessage = formatMessagePVPItem(getMessageFromFile("death.attack.explosion_player_item"), player.getDisplayName(), killer, getKillerWeapon(deathEvent));
            return deathMessage;
        }

        if (originalDeathMessage.contains(magic)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.magic"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(magic_pvp)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.magic_player"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(magic_a_lot)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.even_more_magic"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(wither)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.wither"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(wither_pvp)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.wither_player"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(wither_skull)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.witherSkull"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(anvil)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.anvil"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(anvil_pvp)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.anvil_player"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(fallingBlock)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.fallingBlock"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(fallingBlock_pvp)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.fallingBlock_player"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(stalagmite)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.stalagmite"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(stalagmite_pvp)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.stalagmite_player"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(fallingStalactite)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.fallingStalactite"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(fallingStalactite_pvp)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.fallingStalactite_player"), player.getDisplayName(), killer);
        }

        if (originalDeathMessage.contains(killedby_mob)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.mob"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(killedby_mob_item1) && originalDeathMessage.contains(killedby_mob_item2)){
            deathMessage = formatMessagePVPItem(getMessageFromFile("death.attack.mob_item"), player.getDisplayName(), killer, getKillerWeapon(deathEvent));
            return deathMessage;
        }


        if (originalDeathMessage.contains(killedby_player)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack_player"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(killedby_player_item1) && originalDeathMessage.contains(killedby_player_item2)){
            deathMessage = formatMessagePVPItem(getMessageFromFile("death.attack_player_item"), player.getDisplayName(), killer, getKillerWeapon(deathEvent));
            return deathMessage;
        }

        if (originalDeathMessage.contains(arrow)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.arrow"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(arrow) && originalDeathMessage.contains(arrow_item)){
            deathMessage = formatMessagePVPItem(getMessageFromFile("death.attack.arrow_item"), player.getDisplayName(), killer, getKillerWeapon(deathEvent));
            return deathMessage;
        }

        if (originalDeathMessage.contains(fireball)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.fireball"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(fireball) && originalDeathMessage.contains(fireball_item)){
            deathMessage = formatMessagePVPItem(getMessageFromFile("death.attack.fireball_item"), player.getDisplayName(), killer, getKillerWeapon(deathEvent));
            return deathMessage;
        }

        if (originalDeathMessage.contains(thrown)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.thrown"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(thrown_item)){
            deathMessage = formatMessagePVPItem(getMessageFromFile("death.attack.thrown_item"), player.getDisplayName(), killer, getKillerWeapon(deathEvent));
            return deathMessage;
        }

        if (originalDeathMessage.contains(indirectMagic)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.indirectMagic"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(indirectMagic) && originalDeathMessage.contains(indirectMagic_item)){
            deathMessage = formatMessagePVPItem(getMessageFromFile("death.attack.indirectMagic_item"), player.getDisplayName(), killer, getKillerWeapon(deathEvent));
            return deathMessage;
        }

        if (originalDeathMessage.contains(thorns)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.thorns"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(thorns_hurt) && originalDeathMessage.contains(thorns_item)){
            deathMessage = formatMessagePVPItem(getMessageFromFile("death.attack.thorns_item"), player.getDisplayName(), killer, getKillerWeapon(deathEvent));
            return deathMessage;
        }

        if (originalDeathMessage.contains(trident)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.trident"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(trident) && originalDeathMessage.contains(trident_item_using)){
            deathMessage = formatMessagePVPItem(getMessageFromFile("death.attack.trident_item"), player.getDisplayName(), killer, getKillerWeapon(deathEvent));
            return deathMessage;
        }

        if (originalDeathMessage.contains(fall)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.fall"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(fall_pvp)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.fall_player"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(outOfWorld)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.outOfWorld"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(outOfWorld_pvp)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.outOfWorld_player"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(dragonbreath)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.dragonBreath"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(dragonbreath_pvp)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.dragonBreath_player"), player.getDisplayName(), killer);
            return deathMessage;
        }


        if (originalDeathMessage.contains(flyIntoWall)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.flyIntoWall"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(flyIntoWall_pvp)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.flyIntoWall_player"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(fireworks)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.fireworks"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(fireworks_pvp)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.fireworks_player"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(fireworks_item)){
            deathMessage = formatMessagePVPItem(getMessageFromFile("death.attack.fireworks_item"), player.getDisplayName(), killer, getKillerWeapon(deathEvent));
            return deathMessage;
        }

        if (originalDeathMessage.contains(badRespawnPoint)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.badRespawnPoint.message"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(badRespawnPoint_link)){
            deathMessage = getMessageFromFile("death.attack.badRespawnPoint.link");
            return deathMessage;
        }

        if (originalDeathMessage.contains(berrybush)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.sweetBerryBush"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(berrybush_pvp)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.sweetBerryBush_player"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(sting)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.sting"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(sting_pvp)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.sting_player"), player.getDisplayName(), killer);
            return deathMessage;
        }

        if (originalDeathMessage.contains(freeze)){
            deathMessage = formatMessageNaturalCauses(getMessageFromFile("death.attack.freeze"), player.getDisplayName());
            return deathMessage;
        }

        if (originalDeathMessage.contains(freeze_pvp)){
            deathMessage = formatMessagePVP(getMessageFromFile("death.attack.freeze_player"), player.getDisplayName(), killer);
            return deathMessage;
        }
        log.log("Failed to recognize event death message [!]", LoggingLevel.ERROR);
        return " died apparently?";
    }

    String capitalize(String input){
        return String.valueOf(input.charAt(0)).toUpperCase(Locale.ROOT) + input.substring(1);
    }

    String getKillerWeapon(PlayerDeathEvent deathEvent){
        if (deathEvent.getEntity().getKiller().getItemInUse().getItemMeta().getDisplayName() != null){
            return deathEvent.getEntity().getKiller().getItemInUse().getItemMeta().getDisplayName();
        }else{
            String weaponType = deathEvent.getEntity().getKiller().getItemInUse().getType().name();
            weaponType = weaponType.toLowerCase(Locale.ROOT);
            weaponType = weaponType.replace('_', ' ');
            String[] words = weaponType.split(" ");

            if ( words.length > 1 ){
                StringBuilder resultBuilder = new StringBuilder();
                for (String word: words){
                    resultBuilder.append(capitalize(word));
                    resultBuilder.append(' ');
                }
                if (resultBuilder.charAt(resultBuilder.length()) == ' ') {
                    resultBuilder.deleteCharAt(resultBuilder.length());
                }
                weaponType = resultBuilder.toString();
            }else{
                weaponType = String.valueOf(words[0].charAt(0)).toUpperCase(Locale.ROOT) + words[0].substring(1);
                return weaponType;
            }

            return weaponType;
        }
    }

    String getMessageFromFile(String path){
        return main.deathMessages.getString(path);
    }

    String formatMessageNaturalCauses(String message, String playerName){
        return message.replace("%1$s", playerName);
    }

    // Case: PVP
    String formatMessagePVP(String message, String playerName, String killerName){
        return message.replace("%1$s", playerName).replace("%2$s", killerName);
    }

    //Case PVP + Item
    String formatMessagePVPItem(String message, String playerName, String killerName, String itemName){
        return message.replace("%1$s", playerName).replace("%2$s", killerName).replace("%3$s", itemName);
    }

    //Case {death} + Item
    String formatMessageItem(String message, String playerName, String itemName){
        return message.replace("%1$s", playerName).replace("%2$s", itemName);
    }


}
