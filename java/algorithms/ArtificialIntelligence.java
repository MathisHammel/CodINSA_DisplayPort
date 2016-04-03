package algorithms;

import algorithms.behaviours.*;
import algorithms.pathfinders.FindPathByClosest;
import algorithms.pathfinders.FindPathExploration;
import algorithms.pathfinders.FindPathInterface;
import algorithms.setups.SetupInterface;
import algorithms.setups.SetupLarge;
import algorithms.setups.SetupMedium;
import algorithms.setups.SetupSmall;
import algorithms.worldevaluations.DefensiveEvaluation;
import models.Unit;
import models.World;
import rules.Action;
import models.Game;
import rules.Rules;
import rules.UnitType;
import rules.actions.BuildAction;
import rules.actions.DestroyAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArtificialIntelligence {
    public static Action getNextAction(Game game) {
        return null;
    }

    public static List<Action> getNextActions(Game game) {
        // Calcul des score de chaques cases
        game.getWorld().calculateScores();

        List<Action> actions = new ArrayList<>();
        // Algorithms
        SetupInterface setup;
        BehaviourInterface behaviour;

        // evaluer la stratégie offensive ou defensive
        boolean offensive = true;
        DefensiveEvaluation defensiveEvaluation = new DefensiveEvaluation();
        if (defensiveEvaluation.evaluate(game) > 0.8) {
            //behaviour = new BehaviourDefensive();
            behaviour = new BehaviourOffensive();
        }
        else{
            behaviour = new BehaviourOffensive();
        }
        System.out.println("Offensive ? " + offensive);

        // Detection de la taille et affectation des algorithmes
        int worldSize = game.getWorld().getSize();
        if (worldSize <= World.SIZE_SMALL){
            setup = new SetupSmall();
        }
        else if(worldSize <= World.SIZE_MEDIUM){
            setup = new SetupMedium();
        }
        else{
            setup = new SetupLarge();
        }

        Action set = setup.deploy(game);
        if(set != null) {
            actions.add(set);
        } else {
            // execution des algorithmes communs et  du behaviour selecionné

            // Faire potentiellement construire les ingénieurs sur la pos courante
            actions.addAll(Builders.predictBuilds(game));

            actions.addAll(behaviour.decideActions(game));

            // Déplacement des Scouts
            FindPathInterface findPathScout = new FindPathExploration();
            actions.addAll(findPathScout.evaluatePath(game, null, null));
            // S'il n'y a plus rien à faire
            if(actions.isEmpty()){
                // move all the engineers to the enemy city
                for (Map.Entry<Integer, Unit> intUnit: game.getCurrentPlayer().getUnits(UnitType.ENGINEER).entrySet()) {
                    Unit unit = intUnit.getValue();
                    // si on est sur une ville on attaque
                    //game.bindGame();
                    if(game.getWorld().getCell(unit.getX(),unit.getY()) == game.getOtherPlayer().getCity() && Rules.checkDestroy(game, unit.getId())){
                        // C'est la WIN !
                        actions.add(new DestroyAction(unit));
                        return actions;
                    }
                    // on essaie de bouger l'ingénieur vers la ville
                    FindPathInterface pathEngineers = new FindPathByClosest();
                    actions.addAll(pathEngineers.evaluatePath(game, unit, game.getOtherPlayer().getCity()));
                }
            }
        }

        return actions;
    }
}
