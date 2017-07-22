
# Java_Afficheur_Likes
Affiche les likes d'une slide lors d'une présentation.


<strong>Description : </strong>

Ce composant affiche le nombre de likes de la slide courante. Il reçoit l'ensemble des likes des composants Likers et affichent 
ensuite ceux correspondants à la slide dont le numéro est passé par le composant Récupérateur.
Ce composant présente une interface graphique qui présente un champ de texte affichant le nombre de likes.

<strong>Lancement de l'application : </strong>

Pour lancer l'application, il suffit de lancer le .jar présent dans le dossier target. 
Pour générer celui-ci, lancer la phase maven install de projet.

<strong>Spécifications UPnP : </strong>

Ce composant présente le service UPnP ReceiveLikeService et PageService dont voici la description :

  1) PageService :
    a) public void setPage(String page) : reçoit un message XML par UPnP contenant le numéro de la slide courante.
    
  2) ReceiveLikeService :
    a) public void setPageLike(String likes) : reçoit un message XML par UPnP contenant l'ensemble des slides et leur likes
    respectifs.
    
Voici le schéma représentant le composant :

![alt tag](https://github.com/components-upnp/Java_Afficheur_Likes/blob/master/Afficheur.png)

<strong>Maintenance : </strong>

Ce projet est un projet Maven dont la phase d'exécution install créé l'exécutable de l'application. 
Aucun test unitaire n'a été produit pour l'instant.
