// src/utils/jwt.ts

import jwt from "jsonwebtoken";

const JWT_SECRET = "super_secret_key"; // depois coloque em .env

interface TokenPayload {
  id: number;
  name: string;
  role: string;
}

export function generateToken(payload: TokenPayload) {
  return jwt.sign(payload, JWT_SECRET, {
    expiresIn: "1d",
  });
}

export function verifyToken(token: string) {
  return jwt.verify(token, JWT_SECRET);
}