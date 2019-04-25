package model;

public enum STORY {
    STORYONE("view/resources/storyChooser/StoryOne.JPG"),
    STORYTWO("view/resources/storyChooser/StoryTwo.JPG");

    private String urlStory;
    private STORY(String urlStory){
        this.urlStory = urlStory;
    }
    public String getUrl(){
        return this.urlStory;
    }
}
