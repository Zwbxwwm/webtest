package com.example.test.Drools.Kie;

import com.example.test.common.Response;
import com.example.test.entity.didiOrder;
import org.apache.commons.lang3.StringUtils;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.math.BigDecimal;

public class Base {
    public static KieSession getSession() {
        KieServices kieServices = KieServices.Factory.get();
        KieContainer kieContainer = kieServices.getKieClasspathContainer();
        //kmodule.xml 中定义的 ksession name
        KieSession kieSession = kieContainer.newKieSession("all-rules");
        return kieSession;
    }

    public static KieSession getSession(String agendaGroupName) {
        KieSession session = getSession();
        if (StringUtils.isNoneBlank(agendaGroupName)) {
            session.getAgenda().getAgendaGroup(agendaGroupName).setFocus();
        }
        return session;
    }

    public static void main(String[] args) {

        System.setProperty("drools.dateformat", "dd-MM-yyyy");
        KieSession kieSession = getSession("test-group1");

        didiOrder order = new didiOrder();
        order.setOrderAmount(new BigDecimal("1000"));
        Response response = new Response();
        kieSession.insert(order);
        kieSession.insert(response);
        int count = kieSession.fireAllRules();

        System.out.println(count);
        System.out.println(order.getOrderId());
        System.out.println(response.getData());
        kieSession.dispose();
    }
}