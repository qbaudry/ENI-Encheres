$(document).ready(function() {
    $('#tableUsers').DataTable( {
        "language": {
            "lengthMenu": "_MENU_ lignes par page",
            "zeroRecords": "Aucun utilisateur",
            "info": "page _PAGE_/_PAGES_",
            "infoEmpty": "",
            "infoFiltered": "",
            "search": "Rechercher:",
            "paginate": {
                "first":      "Première",
                "last":       "Dernière",
                "next":       "Suivante",
                "previous":   "Précédante"
            },
            "infoEmpty":      "0 résultas",
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
        	{ "bSortable": true },
        	{ "bSortable": false }
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