import { Router } from "express";
import { upload } from "../../config/upload.config.js";
import {
  createDataSetController,
  getAllDataSetsController,
  approveDataSetController,
  rejectDataSetController,
  viewDataSetController
} from "./dataset.controller.js";
import { authMiddleware } from "../../middlewares/auth.middleware.js";
import { requireAdmin } from "../../middlewares/role.middleware.js";

const datasetRoutes = Router();

/**
 * @swagger
 * tags:
 *   name: Dataset
 *   description: Operações relacionadas a datasets
 */

/**
 * @swagger
 * /dataset:
 *   post:
 *     summary: Criar novo dataset (upload CSV)
 *     tags: [Dataset]
 *     requestBody:
 *       required: true
 *       content:
 *         multipart/form-data:
 *           schema:
 *             type: object
 *             required:
 *               - data
 *               - file
 *             properties:
 *               data:
 *                 type: string
 *                 description: JSON string contendo os dados do dataset
 *               file:
 *                 type: string
 *                 format: binary
 *     responses:
 *       201:
 *         description: Dataset criado com sucesso
 *       400:
 *         description: Dados inválidos
 */
datasetRoutes.post(
  "/",
  upload.single("file"),
  createDataSetController
);

/**
 * @swagger
 * /dataset:
 *   get:
 *     summary: Listar todos os datasets
 *     tags: [Dataset]
 *     security:
 *       - bearerAuth: []
 *     responses:
 *       200:
 *         description: Lista retornada com sucesso
 *       401:
 *         description: Não autenticado
 */
datasetRoutes.get(
  "/",
  authMiddleware,
  getAllDataSetsController
);

/**
 * @swagger
 * /dataset/{id}/approve:
 *   patch:
 *     summary: Aprovar dataset
 *     tags: [Dataset]
 *     security:
 *       - bearerAuth: []
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         schema:
 *           type: integer
 *     responses:
 *       200:
 *         description: Dataset aprovado
 *       403:
 *         description: Apenas ADMIN
 */
datasetRoutes.patch(
  "/:id/approve",
  authMiddleware,
  requireAdmin,
  approveDataSetController
);

/**
 * @swagger
 * /dataset/{id}/reject:
 *   patch:
 *     summary: Rejeitar dataset
 *     tags: [Dataset]
 *     security:
 *       - bearerAuth: []
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         schema:
 *           type: integer
 *     responses:
 *       200:
 *         description: Dataset rejeitado
 *       403:
 *         description: Apenas ADMIN
 */
datasetRoutes.patch(
  "/:id/reject",
  authMiddleware,
  requireAdmin,
  rejectDataSetController
);

/**
 * @swagger
 * /dataset/{id}/view:
 *   get:
 *     summary: Visualizar dataset completo (dados + CSV em JSON)
 *     tags: [Dataset]
 *     security:
 *       - bearerAuth: []
 *     parameters:
 *       - in: path
 *         name: id
 *         required: true
 *         schema:
 *           type: integer
 *     responses:
 *       200:
 *         description: Dataset retornado com CSV convertido
 *       403:
 *         description: Apenas ADMIN
 */
datasetRoutes.get(
  "/:id/view",
  authMiddleware,
  requireAdmin,
  viewDataSetController
);

export default datasetRoutes;