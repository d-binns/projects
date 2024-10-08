class SuperHuman {
	constructor(name, powerLevel) {
		this.name = name;
		this.powerLevel = powerLevel;
	}
}

// Define SuperHero and SuperVillain classes here
class SuperHero extends SuperHuman{
	constructor (name, alias, powerLevel){
		super(name, powerLevel);
		this.alias = alias;	
	}
	
	battle (opponent){
		if (this.powerLevel >= opponent.powerLevel){
			
			return true;
		}
		
		else{
			return false;
		}
	}
	
}

class SuperVillain extends SuperHuman {
	constructor (name, alias, powerlevel){
	super(name, powerlevel);
	this.alias = alias;	
	}
	
	getEvilChuckle (){
		return("Ha ha ha!");
		
	}
}


const heroes = {
	"Super Bacon": new SuperHero("Jack Oinker", "Super Bacon", 2),
	"Flat-Man": new SuperHero("Peter Pranker", "Flat-Man", 5),
	"Mighty Woman": new SuperHero("Diana Dense", "Mighty Woman", 8),
	"Captain Marvelous": new SuperHero("Carol Hangers", "Captain Marvelous", 9)
};

const villains = {
	"The Jokester": new SuperVillain("Jack Nastier", "The Jokester", 3),
	"Magnet Man": new SuperVillain("Max Eisenhardt", "Magnet Man", 6),
	"Lex Loner": new SuperVillain("Lex Loner", "Lex Loner", 2),
	"Thankos": new SuperVillain("Thankos", "Thankos", 9)
};

registerHandlers();

function registerHandlers() {
	// Detect selection of hero and villain
	document.querySelector("#heroSelect").addEventListener("change", selectionChanged);
	document.querySelector("#villainSelect").addEventListener("change", selectionChanged);

	selectionChanged();
}

function selectionChanged() {
	const selectedHeroValue = document.querySelector("#heroSelect").value;
	const selectedVillainValue = document.querySelector("#villainSelect").value;

	// Get hero and villain selected
	const selectedHero = heroes[selectedHeroValue];
	const selectedVillain = villains[selectedVillainValue];

	// Your code goes here
	if (selectedHero.battle(selectedVillain))
	{
		document.getElementById("winner").innerHTML = "Winner: " + selectedHero.alias + "!";
	}
	else{
		
		document.getElementById("winner").innerHTML = "Winner: " + selectedVillain.alias + "!";
	}

}
