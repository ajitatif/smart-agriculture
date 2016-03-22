package org.turkisi.smartagriculture.event;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class Action {

    private String daemon;
    private String command;
    private String[] args;

    public String getDaemon() {
        return daemon;
    }

    public void setDaemon(String daemon) {
        this.daemon = daemon;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        String str = daemon + " " + command;
        if (args != null && args.length > 0) {
            for (String arg : args) {
                str += " " + arg;
            }
        }
        return str;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return !(obj == null || !(obj instanceof Action)) && this.hashCode() == obj.hashCode();
    }
}
