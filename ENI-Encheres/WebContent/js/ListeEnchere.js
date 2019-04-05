$( document ).ready(function() {
	$('#inputGroupSelect').change(function(){
		filtrer();		
	});
	$('#filtre').on('keyup paste',function(){
		filtrer();		
	});
	$('#achat_btn').click(function(){
		$('#encheres_ouvertes').prop('disabled','');
		$('#encheres_encours').prop('disabled','');
		$('#encheres_remportees').prop('disabled','');
		$('#ventes_encours').prop('disabled','disabled');
		$('#ventes_encours').prop('checked',false);
		$('#ventes_non_debutees').prop('disabled','disabled');
		$('#ventes_non_debutees').prop('checked',false);
		$('#ventes_terminees').prop('disabled','disabled');
		$('#ventes_terminees').prop('checked',false);
	});
	$('#vente_btn').click(function(){
		$('#encheres_ouvertes').prop('disabled','disabled');
		$('#encheres_ouvertes').prop('checked',false);
		$('#encheres_encours').prop('disabled','disabled');
		$('#encheres_encours').prop('checked',false);
		$('#encheres_remportees').prop('disabled','disabled');
		$('#encheres_remportees').prop('checked',false);
		$('#ventes_encours').prop('disabled','');
		$('#ventes_non_debutees').prop('disabled','');
		$('#ventes_terminees').prop('disabled','');
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
	$.post( "/ENI-Encheres/Ajax_ListeEnchere",
			{ pseudo:$pseudo,
				mdp:$mdp
				})
				.done(function( data ) {
					$( "#modalProfil" ).html( data );
				});
	$( "#modalProfil" ).modal();
}