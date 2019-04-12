USE [master]
GO

/****** Object:  Database [TROCENCHERE]    Script Date: 12/04/2019 11:26:54 ******/
CREATE DATABASE [TROCENCHERE]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'TROCENCHERE', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\TROCENCHERE.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'TROCENCHERE_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\TROCENCHERE_log.ldf' , SIZE = 1024KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO

ALTER DATABASE [TROCENCHERE] SET COMPATIBILITY_LEVEL = 120
GO

IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [TROCENCHERE].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO

ALTER DATABASE [TROCENCHERE] SET ANSI_NULL_DEFAULT OFF 
GO

ALTER DATABASE [TROCENCHERE] SET ANSI_NULLS OFF 
GO

ALTER DATABASE [TROCENCHERE] SET ANSI_PADDING OFF 
GO

ALTER DATABASE [TROCENCHERE] SET ANSI_WARNINGS OFF 
GO

ALTER DATABASE [TROCENCHERE] SET ARITHABORT OFF 
GO

ALTER DATABASE [TROCENCHERE] SET AUTO_CLOSE OFF 
GO

ALTER DATABASE [TROCENCHERE] SET AUTO_SHRINK OFF 
GO

ALTER DATABASE [TROCENCHERE] SET AUTO_UPDATE_STATISTICS ON 
GO

ALTER DATABASE [TROCENCHERE] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO

ALTER DATABASE [TROCENCHERE] SET CURSOR_DEFAULT  GLOBAL 
GO

ALTER DATABASE [TROCENCHERE] SET CONCAT_NULL_YIELDS_NULL OFF 
GO

ALTER DATABASE [TROCENCHERE] SET NUMERIC_ROUNDABORT OFF 
GO

ALTER DATABASE [TROCENCHERE] SET QUOTED_IDENTIFIER OFF 
GO

ALTER DATABASE [TROCENCHERE] SET RECURSIVE_TRIGGERS OFF 
GO

ALTER DATABASE [TROCENCHERE] SET  DISABLE_BROKER 
GO

ALTER DATABASE [TROCENCHERE] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO

ALTER DATABASE [TROCENCHERE] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO

ALTER DATABASE [TROCENCHERE] SET TRUSTWORTHY OFF 
GO

ALTER DATABASE [TROCENCHERE] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO

ALTER DATABASE [TROCENCHERE] SET PARAMETERIZATION SIMPLE 
GO

ALTER DATABASE [TROCENCHERE] SET READ_COMMITTED_SNAPSHOT OFF 
GO

ALTER DATABASE [TROCENCHERE] SET HONOR_BROKER_PRIORITY OFF 
GO

ALTER DATABASE [TROCENCHERE] SET RECOVERY FULL 
GO

ALTER DATABASE [TROCENCHERE] SET  MULTI_USER 
GO

ALTER DATABASE [TROCENCHERE] SET PAGE_VERIFY CHECKSUM  
GO

ALTER DATABASE [TROCENCHERE] SET DB_CHAINING OFF 
GO

ALTER DATABASE [TROCENCHERE] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO

ALTER DATABASE [TROCENCHERE] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO

ALTER DATABASE [TROCENCHERE] SET DELAYED_DURABILITY = DISABLED 
GO

ALTER DATABASE [TROCENCHERE] SET  READ_WRITE 
GO

USE [TROCENCHERE]
GO

/****** Object:  Table [dbo].[CATEGORIES]    Script Date: 12/04/2019 11:26:30 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[CATEGORIES](
	[no_categorie] [int] IDENTITY(1,1) NOT NULL,
	[libelle] [varchar](30) NOT NULL,
 CONSTRAINT [categorie_pk] PRIMARY KEY CLUSTERED 
(
	[no_categorie] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

/****** Object:  Table [dbo].[ENCHERES]    Script Date: 12/04/2019 11:26:35 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[ENCHERES](
	[no_utilisateur] [int] NOT NULL,
	[no_article] [int] NOT NULL,
	[date_enchere] [datetime] NOT NULL,
	[montant_enchere] [int] NOT NULL,
 CONSTRAINT [enchere_pk] PRIMARY KEY CLUSTERED 
(
	[no_utilisateur] ASC,
	[no_article] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[ENCHERES]  WITH CHECK ADD  CONSTRAINT [encheres_articles_vendus_fk] FOREIGN KEY([no_article])
REFERENCES [dbo].[ARTICLES_VENDUS] ([no_article])
GO

ALTER TABLE [dbo].[ENCHERES] CHECK CONSTRAINT [encheres_articles_vendus_fk]
GO


USE [TROCENCHERE]
GO

/****** Object:  Table [dbo].[RETRAITS]    Script Date: 12/04/2019 11:26:40 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[RETRAITS](
	[no_article] [int] NOT NULL,
	[rue] [varchar](30) NOT NULL,
	[code_postal] [varchar](15) NOT NULL,
	[ville] [varchar](30) NOT NULL,
 CONSTRAINT [retrait_pk] PRIMARY KEY CLUSTERED 
(
	[no_article] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[RETRAITS]  WITH CHECK ADD  CONSTRAINT [retraits_articles_vendus_fk] FOREIGN KEY([no_article])
REFERENCES [dbo].[ARTICLES_VENDUS] ([no_article])
GO

ALTER TABLE [dbo].[RETRAITS] CHECK CONSTRAINT [retraits_articles_vendus_fk]
GO


USE [TROCENCHERE]
GO


/****** Object:  Table [dbo].[UTILISATEURS]    Script Date: 12/04/2019 11:26:44 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[UTILISATEURS](
	[no_utilisateur] [int] IDENTITY(1,1) NOT NULL,
	[pseudo] [varchar](30) NOT NULL,
	[nom] [varchar](30) NOT NULL,
	[prenom] [varchar](30) NOT NULL,
	[email] [varchar](255) NOT NULL,
	[telephone] [varchar](15) NULL,
	[rue] [varchar](30) NOT NULL,
	[code_postal] [varchar](10) NOT NULL,
	[ville] [varchar](30) NOT NULL,
	[mot_de_passe] [varchar](30) NOT NULL,
	[credit] [int] NOT NULL,
	[administrateur] [bit] NOT NULL,
	[banni] [bit] NOT NULL,
 CONSTRAINT [utilisateur_pk] PRIMARY KEY CLUSTERED 
(
	[no_utilisateur] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO


USE [TROCENCHERE]
GO

/****** Object:  Table [dbo].[ARTICLES_VENDUS]    Script Date: 12/04/2019 11:26:18 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[ARTICLES_VENDUS](
	[no_article] [int] IDENTITY(1,1) NOT NULL,
	[nom_article] [varchar](300) NOT NULL,
	[description] [varchar](300) NOT NULL,
	[date_debut_encheres] [datetime] NOT NULL,
	[date_fin_encheres] [datetime] NOT NULL,
	[prix_initial] [int] NULL,
	[prix_vente] [int] NULL,
	[no_vendeur] [int] NOT NULL,
	[no_categorie] [int] NOT NULL,
	[photo] [varchar](2000) NULL,
	[paye] [bit] NOT NULL,
 CONSTRAINT [articles_vendus_pk] PRIMARY KEY CLUSTERED 
(
	[no_article] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[ARTICLES_VENDUS]  WITH CHECK ADD  CONSTRAINT [articles_vendus_categories_fk] FOREIGN KEY([no_categorie])
REFERENCES [dbo].[CATEGORIES] ([no_categorie])
GO

ALTER TABLE [dbo].[ARTICLES_VENDUS] CHECK CONSTRAINT [articles_vendus_categories_fk]
GO

ALTER TABLE [dbo].[ARTICLES_VENDUS]  WITH CHECK ADD  CONSTRAINT [encheres_utilisateur_fk] FOREIGN KEY([no_vendeur])
REFERENCES [dbo].[UTILISATEURS] ([no_utilisateur])
GO

ALTER TABLE [dbo].[ARTICLES_VENDUS] CHECK CONSTRAINT [encheres_utilisateur_fk]
GO

ALTER TABLE [dbo].[ARTICLES_VENDUS]  WITH CHECK ADD  CONSTRAINT [ventes_utilisateur_fk] FOREIGN KEY([no_vendeur])
REFERENCES [dbo].[UTILISATEURS] ([no_utilisateur])
GO

ALTER TABLE [dbo].[ARTICLES_VENDUS] CHECK CONSTRAINT [ventes_utilisateur_fk]
GO

USE [TROCENCHERE]
GO








