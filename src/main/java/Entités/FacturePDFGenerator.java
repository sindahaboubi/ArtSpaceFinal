package Entités;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.DashedBorder;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.BorderCollapsePropertyValue;
import com.itextpdf.layout.property.TextAlignment;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.layout.property.UnitValue;





public class FacturePDFGenerator {

    public static void genererFacture(Commande commande) throws IOException {

        String dest = "facture_" + commande.getIdCommande() + ".pdf";
        PdfWriter writer = new PdfWriter(new FileOutputStream(dest));
        PdfDocument pdf = new PdfDocument(writer);
        // Définir la taille par défaut de la page
        pdf.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pdf);
// header of facture

        float twocol = 285f;

        float twocol158 = twocol+158f;

        float[] twocolumnWidth = {twocol158, twocol};
// Définir la couleur jaune #ffdf21
        Color yellowColor = new DeviceRgb(255, 223, 33);
        // Création de la table avec 2 colonnes
        Table table = new Table(twocolumnWidth);
        // Ajouter le contenu à la facture
        Image logo = new Image(ImageDataFactory.create("C:/Users/soussou/Desktop/ArtSpace/src/main/resources/Layouts/Images/logo.png"));
        logo.setWidth(108);
        logo.setHeight(59);
        // Ajout du logo dans la première cellule
        Cell logoCell = new Cell().add(logo);
        logoCell.setBackgroundColor(yellowColor); // Définir le fond en jaune
        logoCell.setBorder(Border.NO_BORDER);
        table.addCell(logoCell);

        Table table1 = new Table(new float[]{1, 2});
        // Ajout du titre "Facture" dans la deuxième cellule
        Paragraph title = new Paragraph("Facture")
                .setTextAlignment(TextAlignment.LEFT)
                .setFontSize(24)
                .setMarginTop(10); // Ajustez le nombre selon votre préférence
        Cell titleCell = new Cell().add(title);
        titleCell.setTextAlignment(TextAlignment.LEFT);
        titleCell.setBackgroundColor(yellowColor); // Définir le fond en jaune
        titleCell.setBorder(Border.NO_BORDER);
        table.addCell(titleCell);

    // Ajouter la table au document
        document.add(table);
        document.add(table1);

        document.add(new Paragraph("\n"));

// body of facture


        Table parentTable = new Table(UnitValue.createPercentArray(new float[]{1, 1}));
        parentTable.setBorder(Border.NO_BORDER); // Supprimer les bordures autour de la table parente


// Création de la table detailsTable pour les détails du client
        Table detailsTable = new Table(twocolumnWidth).setBorder(Border.NO_BORDER);
        detailsTable.addCell(new Cell().add(new Paragraph("Nom du client : Chaima Riahi")).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
        detailsTable.addCell(new Cell().setBorder(Border.NO_BORDER)); // Cellule vide pour l'espace
        detailsTable.addCell(new Cell().add(new Paragraph("Adresse : Soukra")).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
        detailsTable.addCell(new Cell().setBorder(Border.NO_BORDER)); // Cellule vide pour l'espace
        detailsTable.addCell(new Cell().add(new Paragraph("Numéro de téléphone : 27235624")).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
        detailsTable.addCell(new Cell().setBorder(Border.NO_BORDER)); // Cellule vide pour l'espace
        detailsTable.addCell(new Cell().add(new Paragraph("E-mail : chaimariahi@gmail.com")).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
        detailsTable.addCell(new Cell().setBorder(Border.NO_BORDER)); // Cellule vide pour l'espace
        detailsTable.addCell(new Cell().add(new Paragraph("Code Postal : 2036")).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));


// Création de la table detailsTable1 pour les détails de la facture
        Table detailsTable1 = new Table(twocolumnWidth).setBorder(Border.NO_BORDER);
        detailsTable1.addCell(new Cell().add(new Paragraph("N° de facture : " + commande.getIdCommande())).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
        detailsTable1.addCell(new Cell().setBorder(Border.NO_BORDER)); // Cellule vide pour l'espace
        detailsTable1.addCell(new Cell().add(new Paragraph("Date facturation : " + new SimpleDateFormat("dd/MM/yyyy").format(commande.getDateCommande()))).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));
        detailsTable1.addCell(new Cell().setBorder(Border.NO_BORDER)); // Cellule vide pour l'espace
        detailsTable1.addCell(new Cell().add(new Paragraph("Date Echéance : 31/03/2024")).setTextAlignment(TextAlignment.LEFT).setBorder(Border.NO_BORDER));


// Ajout des tables au parentTable
        parentTable.addCell(detailsTable);
        parentTable.addCell(detailsTable1);

// Ajout de la table parente au document
        document.add(parentTable);



// Ajouter un espace après la table pour créer un décalage
        document.add(new Paragraph("\n"));
// Ajouter une ligne de séparation (divider)
        document.add(createDivider());
        // Ajouter un espace après la table pour créer un décalage
        document.add(new Paragraph("\n"));
        // Ajouter la table des détails de la commande
        document.add(new Paragraph("Détails de la commande "));
        // Création d'une nouvelle table pour les détails des produits

        // Définir la couleur blanche
        Color whiteColor = new DeviceRgb(255, 255, 255);
        // Définir la couleur bleue
        Color blueColor = new DeviceRgb(25, 25, 112);
        //Définir la couleur noire
        Color blackColor = new DeviceRgb(0, 0, 0);
        Table productTable = new Table(new float[]{1, 3, 2, 2});
        productTable.setWidth(UnitValue.createPercentValue(100)); // Définir la largeur de la table à 100% de la page

// Ajout d'une ligne pour les en-têtes de colonne
        Cell qtyCell = new Cell().add(new Paragraph("Qté"));
        qtyCell.setBackgroundColor(blueColor);
        qtyCell.setFontColor(whiteColor);
        productTable.addCell(qtyCell);

        Cell designationCell = new Cell().add(new Paragraph("Désignation"));
        designationCell.setBackgroundColor(blueColor);
        designationCell.setTextAlignment(TextAlignment.CENTER);
        designationCell.setFontColor(whiteColor);
        productTable.addCell(designationCell);

        Cell priceCell = new Cell().add(new Paragraph("Prix Unitaire"));
        priceCell.setBackgroundColor(blueColor);
        priceCell.setTextAlignment(TextAlignment.CENTER);
        priceCell.setFontColor(whiteColor);
        productTable.addCell(priceCell);

        Cell amountCell = new Cell().add(new Paragraph("Montant"));
        amountCell.setBackgroundColor(blueColor);
        amountCell.setTextAlignment(TextAlignment.CENTER);
        amountCell.setFontColor(whiteColor);
        productTable.addCell(amountCell);



// Ajout des détails des produits dans la table et calcul de la somme des montants
        double totalMontant = 0.0;
        for (Product product : commande.getProducts()) {
            productTable.addCell(String.valueOf(product.getQte())).setTextAlignment(TextAlignment.CENTER);
            productTable.addCell(product.getName()).setTextAlignment(TextAlignment.CENTER);
            productTable.addCell(product.getUnitPrice() + " DT").setTextAlignment(TextAlignment.CENTER);
            productTable.addCell(product.getTotalPrice() + " DT");

            // Ajouter le montant du produit au total
            totalMontant += product.getTotalPrice();
        }




// Ajout d'une cellule vide pour le Montant Total
        Cell celluleVide1 = new Cell().add(new Paragraph(""));
        celluleVide1.setBorder(Border.NO_BORDER);
        celluleVide1.setBackgroundColor(whiteColor); // Couleur de fond transparente
        productTable.addCell(celluleVide1);

// Ajout d'une cellule vide pour le Prix Unitaire
        Cell celluleVide2 = new Cell().add(new Paragraph(""));
        celluleVide2.setBorder(Border.NO_BORDER);
        celluleVide2.setBackgroundColor(whiteColor); // Couleur de fond transparente
        productTable.addCell(celluleVide2);
// Ajout de la cellule "Montant Total" avec un arrière-plan jaune
        Cell montantTotal = new Cell().add(new Paragraph("Montant Total"));
        montantTotal.setBackgroundColor(yellowColor);
        montantTotal.setTextAlignment(TextAlignment.CENTER);
        productTable.addCell(montantTotal);

// Ajout de la somme totale à la fin de la colonne Montant
        Cell montantTotalCell = new Cell(1, 2).add(new Paragraph(String.format("%.2f DT", totalMontant)));
        montantTotalCell.setBackgroundColor(yellowColor);
        montantTotalCell.setTextAlignment(TextAlignment.CENTER);
        montantTotalCell.setFontColor(blackColor);
        productTable.addCell(montantTotalCell);

// Ajout de la table des produits au document
        document.add(productTable);
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));

        Paragraph appreciationParagraph1 = new Paragraph("Conditions et modalités de paiement");
        document.add(appreciationParagraph1);
        Paragraph appreciationParagraph3 = new Paragraph("Le paiement est dû dans 30 jours");
        document.add(appreciationParagraph3);

// Ajouter un espace après la table pour créer un décalage
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));
// Ajouter une ligne de séparation (divider)
        document.add(createDivider());

        Paragraph appreciationParagraph = new Paragraph("Nous apprécions votre clientèle.");
        appreciationParagraph.setTextAlignment(TextAlignment.CENTER); // Alignement du texte à gauche
        document.add(appreciationParagraph);
        Paragraph contactParagraph = new Paragraph("Si vous avez des questions sur cette facture, n'hésitez pas à nous contacter.");
        contactParagraph.setTextAlignment(TextAlignment.CENTER); // Alignement du texte à gauche
        document.add(contactParagraph);
        Paragraph TVAParagraph = new Paragraph("Numéro SIRET | Code APE | Numéro TVA Intracommunitaire");
        TVAParagraph .setTextAlignment(TextAlignment.CENTER); // Alignement du texte à gauche
        document.add(TVAParagraph );

        document.close();

        System.out.println("Facture générée avec succès : " + dest);
    }
    private static Paragraph createDivider() {
        Paragraph divider = new Paragraph();
        Color color = new DeviceRgb(225, 223, 33); //
        divider.setBorderBottom(new SolidBorder(color, 2f));
        return divider;
    }

    public static void main(String[] args) throws IOException {
        // Initialisez votre commande avec les données nécessaires
        Commande commande = new Commande();
        // Définir la date de commande à la date actuelle
        commande.setDateCommandeToNow();
        // Ajoutez des produits à la commande
        commande.addProduct(new Product("Champ de blé aux corbeaux ", 1, 100.00));
        commande.addProduct(new Product(" Champ de blé aux corbeaux ", 2, 170.00));
        commande.addProduct(new Product(" Champ de blé aux corbeaux ", 2, 200.00));
        commande.addProduct(new Product(" Champ de blé aux corbeaux ", 2, 300.00));
        commande.addProduct(new Product(" Champ de blé aux corbeaux ", 2, 1000.00));
        commande.addProduct(new Product(" Champ de blé aux corbeaux ", 2, 500.00));
        commande.addProduct(new Product(" Champ de blé aux corbeaux ", 2, 193.00));
        commande.addProduct(new Product(" Champ de blé aux corbeaux ", 2, 118.00));
        genererFacture(commande);
    }
}
