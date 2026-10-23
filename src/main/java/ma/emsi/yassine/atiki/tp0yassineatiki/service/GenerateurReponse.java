package ma.emsi.yassine.atiki.tp0yassineatiki.service; // A modifier

import jakarta.enterprise.context.Dependent;

import java.io.Serializable;
import java.util.Locale;

/**
 * Classe de service pour les modificateurs de question.
 * Un modificateur de question prend en entrée une question et retourne
 * la question modifiée.
 */
@Dependent // Portée CDI pour rendre la classe injectable par CDI dans les autres classes.
// Portée Dependent : l'instance sera supprimée quand la classe qui l'a injectée sera supprimée.
public class GenerateurReponse implements Serializable { // Serializable car CDI peut mettre l'instance en mémoire secondaire.
    /**
     * Génère une réponse à une question.
     * @param question La question à modifier.
     * @param roleSysteme Le rôle système à utiliser pour la modification de la question.
     * @return La question modifiée : le rôle système en majuscule au début de la question, s'il n'est pas null,
     * suivi d'un saut de ligne,
     * puis la question en minuscule, le tout entouré de "||".
     */
    public String genererReponse(String question, String roleSysteme) {
        // Supprimer les espaces au debut et a la fin
        String texte = question.trim();

        //Compter les mots
        String []mots = texte.split(" ");
        int nombreMots = mots.length;
        // Compter les caracteres
        int nombreCaracteres = texte.length();

        // Chercher le mot le plus long
        String motPlusLong = "";

        for (String mot : mots) {
            if (mot.length() > motPlusLong.length()) {
                motPlusLong = mot;
            }
        }

        // Chercher les technologies dans le texte
        String[] technologies = {"java", "jakarta", "spring", "python", "docker", "maven", "git", "react", "angular"};

        String texteMinuscule = texte.toLowerCase();
        String technologiesTrouvees = "";

        for (String technologie : technologies) {
            if (texteMinuscule.contains(technologie)) {
                technologiesTrouvees += technologie + ", ";
            }
        }

        if (technologiesTrouvees.equals("")) {
            technologiesTrouvees = "Aucune";
        } else {
            technologiesTrouvees = technologiesTrouvees.substring(
                    0, technologiesTrouvees.length() - 2
            );
        }

        // Preparer la reponse
        String resultat = "=== ANALYSE DU TEXTE ===\n\n";

        resultat += "Nombre de mots : " + nombreMots + "\n";
        resultat += "Nombre de caracteres : " + nombreCaracteres + "\n";
        resultat += "Mot le plus long : " + motPlusLong + "\n";
        resultat += "Technologies detectees : " + technologiesTrouvees;

        return resultat;
    }


}
