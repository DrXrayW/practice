package xray.leetcode.design;

import java.util.*;

public class AnimalGame {
	public static Scanner in;
	public static void main(String[] args) {
		in = new Scanner(System.in);
		AnimalGame game = new AnimalGame("tiger");
		for(int i=0;i<10;i++){
			game.play();
		}
		in.close();
	}
	
	private DecisionNode root;
	public AnimalGame(String initAnimal){
		root = new DecisionNode(null);
		root.withFeature = new DecisionNode(null);
		root.withFeature.animal = initAnimal;
		root.withFeature.parent = root;
	}
	
	public synchronized void play(){
		DecisionNode node = root.withFeature;
		while(true){
			if(node.isLeaf()){
				ouptut(node.animal);
				NewAnimalInfo feedback = getFeedback(node.animal);
				if(feedback.animal.equals(node.animal)){
					outputWin();
					return;
				}
				
				//split the node
				DecisionNode newNode = new DecisionNode(feedback.feature);
				if(node.parent!=null){
					if(node.parent.withFeature == node){
						node.parent.withFeature = newNode;
					}else{
						node.parent.withoutFeature = newNode;
					}
				}
				
				DecisionNode newLeaf = new DecisionNode(feedback.feature);
				newLeaf.animal = feedback.animal;
				newNode.withFeature = newLeaf;
				newLeaf.parent = newNode;
				newNode.withoutFeature = node;
				node.parent = newNode;
				
				outputLearnt();
				return;
				
			}else{ //not leaf, walk the tree
				boolean hasFeature = askFeature(node.feature);
				if(hasFeature){
					node = node.withFeature;
				}else{
					node = node.withoutFeature;
				}
			}
		}		
	}
	
	private boolean askFeature(String feature) {
		System.out.println("Has this feature: " + feature + "?");
		boolean hasFeature = in.nextBoolean();
		return hasFeature;
	}

	private void outputLearnt() {
		System.out.println("New animal added.  Thanks.");
	}

	private void outputWin() {
		System.out.println("Thank you for playing.");
	}

	private NewAnimalInfo getFeedback(String animal) {
		System.out.println("Is that right?");
		boolean right = in.nextBoolean();
		NewAnimalInfo newAnimalInfo = new NewAnimalInfo(animal, "");
		if(!right){
			System.out.println("Please let me know the new animal name.");
			newAnimalInfo.animal = in.next(); //error handling
			System.out.println("Please let me know the feature.");
			newAnimalInfo.feature = in.next(); //error handling
		}
		
		return newAnimalInfo;
	}

	private void ouptut(String animal) {
		System.out.println("The animal is " + animal + ".");
	}

	class NewAnimalInfo{
		String animal;
		String feature;
		NewAnimalInfo(String animal, String feature){
			this.animal = animal;
			this.feature = feature;
		}
	}
	
	class DecisionNode{
		DecisionNode parent = null;
		String animal = null;
		boolean isLeaf(){
			return animal!=null;
		}
		final String feature;
		DecisionNode(String feature){
			this.feature = feature;
		}
		DecisionNode withFeature = null;
		DecisionNode withoutFeature = null;
	}
}
