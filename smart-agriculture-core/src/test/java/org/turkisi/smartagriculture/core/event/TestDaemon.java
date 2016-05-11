package org.turkisi.smartagriculture.core.event;

import org.turkisi.smartagriculture.daemon.Daemon;

/**
 * @author Gökalp Gürbüzer (gokalp.gurbuzer@yandex.com)
 */
public class TestDaemon implements Daemon {

    private ActOnEventSteps testSuite;

    TestDaemon(ActOnEventSteps testSuite) {
        this.testSuite = testSuite;
    }

    @Override
    public String getDaemonName() {
        return "watering";
    }

    @Override
    public void invokeAction(String command, String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String arg : args) {
            stringBuilder.append(" ").append(arg);
        }
        String cmd = getDaemonName() + " " + command + " " + stringBuilder.toString();
        testSuite.getResultRegister().setCommand(cmd.trim());
        synchronized (testSuite.getResultRegister()) {
            testSuite.getResultRegister().notify();
        }
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
