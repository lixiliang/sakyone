/**
 * Created by lixiliang on 2019/1/11.
 */
public class Entity {
    private String name;
    private String text;

    public Entity(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Entity{" + "name='" + name + '\'' + ", text='" + text + '\'' + '}';
    }
}
