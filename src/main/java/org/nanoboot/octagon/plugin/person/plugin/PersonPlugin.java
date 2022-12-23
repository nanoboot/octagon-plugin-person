///////////////////////////////////////////////////////////////////////////////////////////////
// Octagon Plugin Person: Person plugin for Octagon application.
// Copyright (C) 2021-2022 the original author or authors.
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation; version 2
// of the License only.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
///////////////////////////////////////////////////////////////////////////////////////////////

package org.nanoboot.octagon.plugin.person.plugin;

import java.util.Properties;
import org.nanoboot.octagon.plugin.api.core.Plugin;
import org.nanoboot.octagon.plugin.api.core.PluginStub;
import org.nanoboot.octagon.plugin.api.core.PluginStubImpl;
import org.nanoboot.octagon.plugin.person.classes.Inbox;
import org.nanoboot.octagon.plugin.person.classes.Licence;
import org.nanoboot.octagon.plugin.person.classes.PersonTask;
import org.nanoboot.octagon.plugin.person.classes.ReadingDiary;
import org.nanoboot.octagon.plugin.person.classes.Thing;
import org.nanoboot.octagon.plugin.person.classes.Todo;
import org.nanoboot.octagon.plugin.person.persistence.impl.mappers.*;
import org.nanoboot.octagon.plugin.person.persistence.impl.repos.InboxRepositoryImplSQLiteMyBatis;
import org.nanoboot.octagon.plugin.person.persistence.impl.repos.LicenceRepositoryImplSQLiteMyBatis;
import org.nanoboot.octagon.plugin.person.persistence.impl.repos.PersonTaskRepositoryImplSQLiteMyBatis;
import org.nanoboot.octagon.plugin.person.persistence.impl.repos.ReadingDiaryRepositoryImplSQLiteMyBatis;
import org.nanoboot.octagon.plugin.person.persistence.impl.repos.ThingRepositoryImplSQLiteMyBatis;
import org.nanoboot.octagon.plugin.person.persistence.impl.repos.TodoRepositoryImplSQLiteMyBatis;
import org.nanoboot.octagon.plugin.person.persistence.impl.typehandlers.ReadingDiaryTypeTypeHandler;
import org.nanoboot.octagon.plugin.person.persistence.impl.typehandlers.ReminderTypeTypeHandler;
import org.nanoboot.powerframework.json.JsonObject;
import lombok.Getter;

/**
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.1.0
 */
public class PersonPlugin implements Plugin {
    private static final String PERSON = "person";
    @Getter
    private PluginStub pluginStub = new PluginStubImpl();

    @Override
    public String getGroup() {
        return PERSON;
    }

    @Override
    public String getId() {
        return PERSON;
    }

    @Override
    public String getVersion() {
        return "0.0.0";
    }

    @Override
    public String init(Properties propertiesConfiguration) {

        pluginStub.registerEntityGroup(PERSON, 30);

        int sortkeyInGroup = 10;
        pluginStub
                .registerEntity(
                        PERSON,
                        PersonTask.class,
                        PersonTaskMapper.class,
                        PersonTaskRepositoryImplSQLiteMyBatis.class, sortkeyInGroup++);
        pluginStub
                .registerEntity(
                        PERSON,
                        Thing.class,
                        ThingMapper.class,
                        ThingRepositoryImplSQLiteMyBatis.class, sortkeyInGroup++);
        pluginStub
                .registerEntity(
                        PERSON,
                        Inbox.class,
                        InboxMapper.class,
                        InboxRepositoryImplSQLiteMyBatis.class, sortkeyInGroup++);
        pluginStub
                .registerEntity(
                        PERSON,
                        Todo.class,
                        TodoMapper.class,
                        TodoRepositoryImplSQLiteMyBatis.class, sortkeyInGroup++);
        pluginStub
                .registerEntity(
                        PERSON,
                        ReadingDiary.class,
                        ReadingDiaryMapper.class,
                        ReadingDiaryRepositoryImplSQLiteMyBatis.class, sortkeyInGroup++);
        pluginStub
                .registerEntity(
                        PERSON,
                        Licence.class,
                        LicenceMapper.class,
                        LicenceRepositoryImplSQLiteMyBatis.class, sortkeyInGroup++);


        pluginStub.registerTypeHandler(ReadingDiaryTypeTypeHandler.class);
        pluginStub.registerTypeHandler(ReminderTypeTypeHandler.class);

        return null;
    }

    @Override
    public String getDependsOn() {
        return "task";
    }
    
    @Override
    public boolean hasMigrationSchema() {
        return true;
    }
    
}
