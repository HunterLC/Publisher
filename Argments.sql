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

Date: 2019-04-05 16:21:49
*/


-- ----------------------------
-- Table structure for Argments
-- ----------------------------
DROP TABLE [dbo].[Argments]
GO
CREATE TABLE [dbo].[Argments] (
[ID] int NOT NULL IDENTITY(1,1) ,
[num] int NULL ,
[days] int NULL ,
[lock_days] int NULL 
)


GO

-- ----------------------------
-- Records of Argments
-- ----------------------------
SET IDENTITY_INSERT [dbo].[Argments] ON
GO
INSERT INTO [dbo].[Argments] ([ID], [num], [days], [lock_days]) VALUES (N'1', N'4', N'4', N'2')
GO
GO
SET IDENTITY_INSERT [dbo].[Argments] OFF
GO

-- ----------------------------
-- Indexes structure for table Argments
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table Argments
-- ----------------------------
ALTER TABLE [dbo].[Argments] ADD PRIMARY KEY ([ID])
GO
