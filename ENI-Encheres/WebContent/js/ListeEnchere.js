$( document ).ready(function() {
	$('#inputGroupSelect').change(function(){
		filtrer();		
	});
	$('#filtre').on('keyup paste',function(){
		filtrer();		
	});
	$('#achat_btn').click(function(){
		$('#achat').prop('checked','checked');
		$('#encheres_ouvertes').prop('disabled','');
		$('#encheres_encours').prop('disabled','');
		$('#encheres_remportees').prop('disabled','');
		$('#ventes_encours').prop('disabled','disabled');
		$('#ventes_encours').prop('checked',false);
		$('#ventes_non_debutees').prop('disabled','disabled');
		$('#ventes_non_debutees').prop('checked',false);
		$('#ventes_terminees').prop('disabled','disabled');
		$('#ventes_terminees').prop('checked',false);
		filtrer();
	});
	$('#vente_btn').click(function(){
		$('#vente').prop('checked','checked');
		$('#encheres_ouvertes').prop('disabled','disabled');
		$('#encheres_ouvertes').prop('checked',false);
		$('#encheres_encours').prop('disabled','disabled');
		$('#encheres_encours').prop('checked',false);
		$('#encheres_remportees').prop('disabled','disabled');
		$('#encheres_remportees').prop('checked',false);
		$('#ventes_encours').prop('disabled','');
		$('#ventes_non_debutees').prop('disabled','');
		$('#ventes_terminees').prop('disabled','');
		filtrer();
	});
	$(':checkbox').change(function() {
		filtrer();
	}); 
	$('#achat_btn').click();

	filtrer();
});

function filtrer(){

	$.post( "/ENI-Encheres/Ajax_ListeEnchere",
			{ categ : $('#inputGroupSelect').val(),filtre : $('#filtre').val(),
		achat_vente : $("input[name='options']:checked").prop('id'),		
		eOuvertes:$('#encheres_ouvertes').is(':checked'),
		eEnCours:$('#encheres_encours').is(':checked'),
		eFermees:$('#encheres_remportees').is(':checked'),
		vEnCours:$('#ventes_encours').is(':checked'),
		vNonDebutees:$('#ventes_non_debutees').is(':checked'),
		vTerminees:$('#ventes_terminees').is(':checked')
			})
			.done(function( data ) {
				$( "#listeArticle" ).html( data );
			});

}
function viewProfil(pseudo,mdp){
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