package com.image.viever;import java.util.ArrayList;


public class HistoryManager {
	
	private ArrayList<ImageWrapper> history;
	private int currentStep;
	
	public HistoryManager() {
		history=new ArrayList<ImageWrapper>();
	}
	
	public void add(ImageWrapper image){
		ImageWrapper img= ImageWrapper.clone(image);
		if(history.size()==0){
			currentStep=0;
			history.add(img);
			return;
		}
		if((currentStep+1)<=history.size())
			history.subList(currentStep+1, history.size()).clear();
		history.add(img);
		currentStep=currentStep+1;
	}
	public ImageWrapper getCurrentVersion(){
		return history.get(currentStep);
	}
	
	public Boolean canUndo(){
		if (currentStep>0)
			return true;
		else
			return false;
	}
	
	public Boolean canRedo(){
		if (currentStep<(history.size()-1))
			return true;
		else
			return false;
	}
	
	public void redo(){
		if (canRedo())
			currentStep=currentStep+1;
	}
	
	public void undo(){
		if (canUndo())
			currentStep=currentStep-1;
	}
	
	public void eraseAll(){
		history.clear();
	}
	public int getCurrentStep(){
		return currentStep;
	}
	public int getHistorySize(){
		return history.size();
	}
}
