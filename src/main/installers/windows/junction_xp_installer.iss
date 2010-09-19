[Setup]
MinVersion=0,5.01.2600sp1
AppCopyright=Junction Networks
AppName=JCTN Bulk User Uploader
AppVerName=1.0
PrivilegesRequired=poweruser
DefaultGroupName=Junction Networks
AlwaysUsePersonalGroup=true
ShowLanguageDialog=yes
AppPublisher=Junction Networks
AppPublisherURL=http://junctionnetworks.com
AppSupportURL=http://junctionnetworks.com
AppUpdatesURL=http://junctionnetworks.com
AppVersion=1.0
AppID={{C3A8E200-B1E0-4939-9360-74E25C2FBBBA}
UninstallDisplayName=JCTN Bulk User Uploader
DefaultDirName={pf}\Junction Networks
OutputBaseFilename=BulkUserUploaderSetup
OutputDir=C:\work\clients\junction_networks\projects\bulk_uploads\JunctionBulkUpload\dist\windows
SetupLogging=true
UninstallLogMode=append
VersionInfoVersion=1.0
VersionInfoCompany=Junction Networks
VersionInfoDescription=Bulk user uploader
VersionInfoTextVersion=1.0
VersionInfoCopyright=2010 Junction Networks
VersionInfoProductName=JCTN Bulk User Uploader
VersionInfoProductVersion=1.0

[Files]
Source: C:\work\clients\junction_networks\projects\bulk_uploads\JunctionBulkUpload\target\BulkUserAdd-1.0-jar-with-dependencies.jar; DestDir: {app}; MinVersion: 0,5.01.2600; Flags: comparetimestamp overwritereadonly promptifolder; 
Source: C:\work\clients\junction_networks\projects\bulk_uploads\JunctionBulkUpload\excel\BulkAddUsers.xlsm; DestDir: {app}; Flags: comparetimestamp overwritereadonly promptifolder; 
Source: jctn.ico; DestDir: {app}
[UninstallDelete]
Type: filesandordirs; Name: {pf}\Junction Networks

[Icons]
Name: {group}\JCTN Uploader GUI; Filename: {app}\BulkUserAdd-1.0-jar-with-dependencies.jar; WorkingDir: {app}; IconFilename: {app}\BulkUserAdd-1.0-jar-with-dependencies.jar
Name: {group}\JCTN Bulk User List; Filename: {app}\BulkAddUsers.xlsm; IconFilename: {app}\jctn.ico; Flags: createonlyiffileexists; WorkingDir: {app}
Name: {group}\Uninstall; Filename: {uninstallexe}
