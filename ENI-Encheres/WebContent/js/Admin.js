$(document).ready(function() {
    $('#tableUsers').DataTable( {
        "language": {
            "lengthMenu": "_MENU_ Lignes par page",
            "zeroRecords": "Aucun utilisateur",
            "info": "Page _PAGE_/_PAGES_",
            "infoEmpty": "",
            "infoFiltered": "",
            "search": "Rechercher:",
            "paginate": {
                "first":      "Première",
                "last":       "Dernière",
                "next":       "Suivante",
                "previous":   "Précédante"
            },
            "infoEmpty":      "0 Résultas",
            "infoPostFix":    "",
            "thousands":      " ",
        },
        "aoColumns": [
        	{ "bSortable": true },
        	{ "bSortable": true },
        	{ "bSortable": true },
        	{ "bSortable": true },
        	{ "bSortable": true },
        	{ "bSortable": true },
        	{ "bSortable": true },
        	{ "bSortable": true },
        	{ "visible":false },
        	{ "bSortable": true },
        	{ "bSortable": true }
        ]
    });
    
    $('#tableCategories').DataTable( {
        "language": {
            "lengthMenu": "_MENU_ Lignes par page",
            "zeroRecords": "Aucune categorie",
            "info": "Page _PAGE_/_PAGES_",
            "infoEmpty": "",
            "infoFiltered": "",
            "search": "Rechercher:",
            "paginate": {
                "first":      "Première",
                "last":       "Dernière",
                "next":       "Suivante",
                "previous":   "Précédante"
            },
            "infoEmpty":      "0 Résultas",
            "infoPostFix":    "",
            "thousands":      " ",
        },
        "aoColumns": [
        	{ "bSortable": true },
        	{ "bSortable": true }
        ]
    });
} );

function modifUser(pseudo,mdp){
	$.post( "/ENI-Encheres/ajax_profil",
			{ pseudo:pseudo,
			  mdp:mdp
			})
			.done(function( data ) {
				$( "#corpsModal" ).html( data );
				$('#titreModal').html(pseudo);
			});
	$("#modalProfil").modal();
}

function deleteUser(pseudo,mdp){
	$.post( "/ENI-Encheres/supprimerCompte",
			{ login:pseudo,
			  mdp:mdp
			})
			.done(function( data ) {
				location.reload(true);
			});
}

function banUser(pseudo,mdp){
	$.post( "/ENI-Encheres/bannirCompte",
			{ login:pseudo,
			  mdp:mdp
			})
			.done(function( data ) {
				location.reload(true);
			});
}

function adminUser(pseudo,mdp){
	$.post( "/ENI-Encheres/droitsCompte",
			{ login:pseudo,
			  mdp:mdp
			})
			.done(function( data ) {
				location.reload(true);
			});
}