package oopintro;

class Organism {
	Organism() {
		System.out.println("Creating organism");
	}
}

class Animal extends Organism {
	protected String sound = "";

	Animal(String sound) {
		this.sound = sound;
	}

	public final void hello() {
		System.out.println(sound);
	}
}

class Canine extends Animal {
	Canine(String sound) {
		super(sound);
	}
}

class Dog extends Canine {
	Dog() {
		super("Wofff");
	}
}

class Wolf extends Canine {
	Wolf() {
		super("Growl");
	}
}

class Feline extends Animal {
	Feline(String sound) {
		super(sound);
	}
}

class Lion extends Feline {
	Lion() {
		super("Roarrr");
	}
}

class Cat extends Feline {
	Cat() {
		super("Meowww");
	}
}

public class Exercise05 {
	public static void main(String[] args) {
		Animal[] animals = { new Dog(), new Wolf(), new Cat(), new Lion() };

		/*for (int i = 0; i < animals.length; i++) {
			Animal a = animals[i];
			a.hello();
		}*/

		for (Animal a : animals) {
			a.hello();
		}
	}
}
