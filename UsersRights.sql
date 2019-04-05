/*
Navicat SQL Server Data Transfer

Source Server         : DBtest
Source Server Version : 140000
Source Host           : :1433
Source Database       : publisher
Source Schema         : dbo

Target Server Type    : SQL Server
Target Server Version : 140000
File Encoding         : 65001

Date: 2019-04-05 16:21:40
*/


-- ----------------------------
-- Table structure for UsersRights
-- ----------------------------
DROP TABLE [dbo].[UsersRights]
GO
CREATE TABLE [dbo].[UsersRights] (
[ID] int NOT NULL IDENTITY(1,1) ,
[userID] int NULL ,
[rightID] int NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[UsersRights]', RESEED, 11)
GO

-- ----------------------------
-- Records of UsersRights
-- ----------------------------
SET IDENTITY_INSERT [dbo].[UsersRights] ON
GO
INSERT INTO [dbo].[UsersRights] ([ID], [userID], [rightID]) VALUES (N'1', N'1', N'1')
GO
GO
INSERT INTO [dbo].[UsersRights] ([ID], [userID], [rightID]) VALUES (N'2', N'1', N'2')
GO
GO
INSERT INTO [dbo].[UsersRights] ([ID], [userID], [rightID]) VALUES (N'3', N'1', N'3')
GO
GO
INSERT INTO [dbo].[UsersRights] ([ID], [userID], [rightID]) VALUES (N'4', N'2', N'1')
GO
GO
INSERT INTO [dbo].[UsersRights] ([ID], [userID], [rightID]) VALUES (N'5', N'2', N'4')
GO
GO
INSERT INTO [dbo].[UsersRights] ([ID], [userID], [rightID]) VALUES (N'6', N'3', N'2')
GO
GO
INSERT INTO [dbo].[UsersRights] ([ID], [userID], [rightID]) VALUES (N'7', N'3', N'6')
GO
GO
INSERT INTO [dbo].[UsersRights] ([ID], [userID], [rightID]) VALUES (N'8', N'3', N'7')
GO
GO
INSERT INTO [dbo].[UsersRights] ([ID], [userID], [rightID]) VALUES (N'9', N'1', N'6')
GO
GO
INSERT INTO [dbo].[UsersRights] ([ID], [userID], [rightID]) VALUES (N'10', N'1', N'7')
GO
GO
INSERT INTO [dbo].[UsersRights] ([ID], [userID], [rightID]) VALUES (N'11', N'1', N'8')
GO
GO
SET IDENTITY_INSERT [dbo].[UsersRights] OFF
GO

-- ----------------------------
-- Indexes structure for table UsersRights
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table UsersRights
-- ----------------------------
ALTER TABLE [dbo].[UsersRights] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Foreign Key structure for table [dbo].[UsersRights]
-- ----------------------------
ALTER TABLE [dbo].[UsersRights] ADD FOREIGN KEY ([rightID]) REFERENCES [dbo].[Rights] ([id]) ON DELETE CASCADE ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[UsersRights] ADD FOREIGN KEY ([userID]) REFERENCES [dbo].[Users] ([ID]) ON DELETE CASCADE ON UPDATE CASCADE
GO
