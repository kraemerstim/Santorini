$(document).ready(function(){
	var game = {
		fields: ["a1","a2","a3","a4","a5","b1","b2","b3","b4","b5","c1","c2","c3","c4","c5","d1","d2","d3","d4","d5","e1","e2","e3","e4","e5",],
		levels: [0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0],
		workers1: [],
		workers2: [],
		init: function() {
			game.initBoard();			
			game.clickHandlers();
		},
		initBoard: function() {
			for(i = 0; i < game.levels.length; i ++) {
				$('.board').append('<div id="field'+i+'" class="field field'+i+' level'+game.levels[i]+'"><div id="worker" class="worker"></div></div>');
			}
		},
		updateBoard: function() {
			for(i = 0; i < game.levels.length; i ++) {
				$('.field'+i).removeClass('level0');
				$('.field'+i).removeClass('level1');
				$('.field'+i).removeClass('level2');
				$('.field'+i).removeClass('level3');
				$('.field'+i).removeClass('level4').addClass('level'+game.levels[i]);
			}
			for(i = 0; i < game.fields.length; i ++) {
				$('.field'+i).find('.worker').removeClass('worker1');
			}
			for(i = 0; i < game.workers1.length; i ++) {
				var index = game.getIndex(game.workers1[i]);
				$('.field'+index).find('.worker').addClass('worker1');
			}
			for(i = 0; i < game.fields.length; i ++) {
				$('.field'+i).find('.worker').removeClass('worker2');
			}
			for(i = 0; i < game.workers2.length; i ++) {
				var index = game.getIndex(game.workers2[i]);
				$('.field'+index).find('.worker').addClass('worker2');
			}
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
		getIndex: function(field) {
			for(i = 0; i < game.fields.length; i ++) {
				if (game.fields[i] == field) {
					return i;
				}
			}
			return -1;
		}
	};
	game.init();
});