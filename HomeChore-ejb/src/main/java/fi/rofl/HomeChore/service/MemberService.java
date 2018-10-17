/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fi.rofl.HomeChore.service;

import fi.rofl.HomeChore.model.Collective;
import fi.rofl.HomeChore.model.Member;
import fi.rofl.HomeChore.model.Task;
import fi.rofl.HomeChore.model.PreRegistration;

import fi.rofl.HomeChore.util.ChoreNamedQueries;
import fi.rofl.HomeChore.util.DateUtils;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.mindrot.jbcrypt.BCrypt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class MemberService {

    @Inject
    private Logger log;

    @Inject
    private EntityManager em;

    @Inject
    private Event<Member> memberEventSrc;

    public void register(Member member) throws Exception {
        log.info("Registering " + member.getName());
        
        String hashed = BCrypt.hashpw(member.getPsw(), BCrypt.gensalt());
        member.setPsw(hashed);
        em.persist(member);
        memberEventSrc.fire(member);
    }
    
    public String getMemberCollective(Member member) {
    	
    	Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    	
    	Member fetchedMember = em.find(Member.class, member.getId());
    	if (fetchedMember != null) {
    		List<Collective> collectives = fetchedMember.getCollectives();
    		String json = gson.toJson(collectives);
    		return json;
    	}
    	return null;
    }
    
    public String getPreRegistrations(Member member) {
    	
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

		List<Collective> result = new ArrayList<>();
		member = em.find(Member.class, member.getId());
		List<PreRegistration> preRegistrations = em.createNamedQuery(ChoreNamedQueries.PREREGISTRATIONS_GET_BY_EMAIL, PreRegistration.class)
				.setParameter("email", member.getEmail())
				.getResultList();
		
		if (preRegistrations != null) {
			for (PreRegistration reg : preRegistrations) {
				Collective col = em.find(Collective.class, reg.getId().getCollectiveId());
				if (col != null) {
					result.add(col);
				}
			}
			
		}
		String json = gson.toJson(result);
		return json;
    	
    }
}
