$(document).ready(function(){
	var game = {
		fields: ["a1","a2","a3","a4","a5","b1","b2","b3","b4","b5","c1","c2","c3","c4","c5","d1","d2","d3","d4","d5","e1","e2","e3","e4","e5"],
		levels: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
		workers1: [],
		workers2: [],
		init: function() {
			game.initBoard();			
			game.clickHandlers();
		},
		initBoard: function() {
			for(i = 0; i < game.fields.length; i++) {
				$('.board').append('<div id="field'+i+'" class="field field'+i+' level'+game.levels[i]+'"><div id="worker" class="worker"></div></div>');
			}
		},
		updateBoard: function() {
			game.updateLevels();
			game.updateWorkers(game.workers1, 'worker1');
			game.updateWorkers(game.workers2, 'worker2');
		},
		loadGame: function() {
			$.ajax({
				'async': false,
				'global': false,
				'url': 'json/game.json',
				'dataType': 'json',
				'success': function (data) {
					game.levels = data.levels;
					game.workers1 = data.workers.player1;
					game.workers2 = data.workers.player2;	
				}
			});
		},
		clickHandlers: function() {
			$('.field').on('click', function() {
				game.loadGame();
				game.updateBoard();
			});
		},
		updateLevels: function() {		
			for(field = 0; field < game.fields.length; field++) {
				game.removeLevels('.field'+field);
				game.setLevel('.field'+field, game.levels[field]);
			}
		},
		removeLevels: function(field) {
			for(level = 0; level < 5; level++) {
				$(field).removeClass('level'+level);	
			}
		},
		setLevel: function(field, level) {
			$(field).addClass('level'+level);
		},
		updateWorkers: function(workers, cls) {
			for(field = 0; field < game.fields.length; field++) {
				$('.field'+field).find('.worker').removeClass(cls);
			}
			for(field = 0; field < workers.length; field++) {
				index = game.getIndex(workers[field]);
				$('.field'+index).find('.worker').addClass(cls);
			}
		},
		getIndex: function(field) {
			for(j = 0; j < game.fields.length; j ++) {
				if (game.fields[j] == field) {
					return j;
				}
			}
			return -1;
		}
	};
	game.init();
});