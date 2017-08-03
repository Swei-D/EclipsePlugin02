# EclipsePlugin02

# 1
Hello every one, I am so glad to have all of you here today. And I'd like to share you something about what I have done last month. Its a flow designer for presto business. its an eclipse plug-in. As mentioned in my goal settings, this designer must include a business flow viewer and nice to have a business flow editor.

# 2
To begin with, let me show you how to start this eclipse plug-in. For instance, if we want to open the job configuragtion file of Jump-ldposlmt-korea.xml with this plug-in, we may just select the xml file and click the right mouse button to chose “JumpFlow” in the "Open With" tab for the first time we open this file. And we may double click the file to open with "JumpFlow" in the future.

# 3
Next, let's have quick glance at the compositions of this plugin. As we can see from the picture, there are five parts in this plugin, they are viewer, palette, outline, property and zoom in/out part.

# 4
And then, let's see one of the element definition in xml schema definition (xsd) and how element fits into the data model of this plugin. As we can see from this picture, task element extends from ActionContainerConfig, and holds more or less 4 attributes, such as "name", "dependsOn", "optional" and "anyAttribute" (it mainly refers to "e3:executor" in the job configure file); besides, the task element contains several sub-elements, for example, "Title", "Description", "ActionContainerEntries", "SupplementalConfig". Since the "Title" and "Description" element do not hold any sub-elements, and in order to make the viewer more concise, in this plugin, we map this two elements into properties, so, we can view and edit this two element in the same way as properties do. So we can see 6 properties of task element in the property part. And all the other sub-element of task element will be shown in the viewer part.

# 5
For this page, let's look carefully into the actionContainerEntries, there are five potential types of actionContainerEntries, they are Action, Parallel, For, Switch and Template. We can see the details in these pictures. And we are easy to notice that there is a tree-root（named ActionContainer) that manages all the sub-trees. And each sub-tree has its own properties and sub-elements. For example, from Parallel -> Pipe -> Action -> Input -> Map -> inputs.

# 6
For this page, let me explain the naming rules of figures detailedly.
The name of Taskgroup figure starts from taskgroup1 to taskgroupn according to the orders of their appearance, for example, taskgroup1
The name of Task figure is composed of Task and the "name" attribute of Task Element, for example, Task-getRTPData
The name of Action figure is composed of Action and the "funName" attribute of Action Element, Action-transformationAction
The name of Template figure is composed of Template and the "name" attribute of Template Element, for example, Template-FetchRTPDataByMarket
Since the For element contains no attributes, so we simply name the For figure as For
And the same way for Switch, Case, Parallel, Pipe, Map and List figures
The name of Input figure is composed of Input and the "name" or "templateName" or "const" attributes of Input Element, for example, Input-positions
The name of Output figure is composed of Output and the "ref" attribute of Output Element, for example, Output-clientMarket
The name of DisplayFile is composed of DisplayFile and the "name" attribute of DisplayFile Element, for example, DisplayFile-reportPath
The name of DisplayData is composed of DisplayData and the "name" attribute of DisplayData Element, for example, DisplayData-dataSet

# 7
As we can see from this picture, there are 4 taskgroups and each taskgorup contains one or more than one task. The different taskgroups are layouted in vertical format, and the tasks in the same taskgroup are layouted in horizontal format. The spaces between every two adjacent taskgroups or tasks in the same taskgroup will be set to a fixed value when they are formatted. In addition, we use connections to represent the dependencies between different tasks, from parent to child. For example, in this picture, the task of filterPositions depends on getRTPData, we may notice there is an arrow starts from getRTPData and points to filterPositions. And the direction means that the task of getRTPData should be completed before filterPositions task.

# 8
For this picture, we may have a clear understanding how outline corresponds to viewer. And if an element has sub-elements, there will be an "add" image to the left of figures in viewer, and a small "triangle" to the left of text in outline.

# 9
If we want to modify the properties, we may just select the element in the outline or viewer. For example, we select the task of enrichProducInfos, and we change the potential properties in the property page, and then, we press the "Enter" button to make this change take effect.

# 10
This slide lists the support actions of this eclipse plugin.
1) Expand/Collapse the figures
2) Move figures
3) Drag the whole viewer
4) Zoom in/out
5) Add/Delete connections dynamically
6) Re-layout of taskgroups and tasks
7) Undo/Redo
8) Highlight connection
9) Show/Hide/Move the palette

# 11
This plugin supports several dialogs: if we are trying to open an abstract job, we may receive an error. if we are trying to open a jump configuration file without a corresponding xds file in the same directory, we may receice an error with detailed information too. And if we are trying to save the modified jump configuration file, we may receive a question dialog to confirm this operation.

# 12
To begin with, let’s open one jump configuration file with jump flow designer.
Firstly, let’s demonstrate the expand/collapse action on figures. When the task figure is expanded, we may see its sub-trees or sub-figures.
Secondly, let’s demonstrate the drag and move actions of the views.
Thirdly, let’s demonstrate how to format the layout. If we want to re-layout the viewer, we may just double click the header of figure.
And then, let’s demonstrate the zoom in/out part. If we want to see the connections more clearly or in a global view, we may just zoom out the whole viewer.
Then, let’s demonstrate the action of add/delete connections or figures. 
And redo/undo action.
And then, let’s demonstrate the editor part. For example, if we want to modify the properties of task figure, we may just show the property part, and add something to the description property, and then we just press “Enter” button to make this changes take effect.
Finally, how the viewer interacts with outline and property part. Please look carefully at the outline part first, when we expand the task of filterPositions in taskgroup2, we may notice the taskgroup2 in the outline part will be expanded too. And if we chose one of the sub-element in the viewer part, the corresponding element in the outline part will be choose too.
For now, we may notice the enrichProductInfos only depends on filterPositions, and if we add a new connection from getRTPData to enrichProductInfos, we may notice there is new dependency in the dependsOn attribute of enrichProductInfos. And we may dynamically delete the connection too.

# 13
Are there anyone have questions.

