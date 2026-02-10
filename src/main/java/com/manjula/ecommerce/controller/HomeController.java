package com.manjula.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    
    @GetMapping("/")
    public ResponseEntity<String> home() {
        String html = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>ECommerce REST API - Item Management</title>
                <style>
                    * {
                        margin: 0;
                        padding: 0;
                        box-sizing: border-box;
                        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                    }
                    
                    body {
                        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                        min-height: 100vh;
                        display: flex;
                        justify-content: center;
                        align-items: center;
                        padding: 20px;
                    }
                    
                    .container {
                        background: white;
                        border-radius: 20px;
                        box-shadow: 0 20px 60px rgba(0,0,0,0.3);
                        overflow: hidden;
                        max-width: 900px;
                        width: 100%;
                    }
                    
                    .header {
                        background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
                        color: white;
                        padding: 40px;
                        text-align: center;
                    }
                    
                    .header h1 {
                        font-size: 2.5rem;
                        margin-bottom: 10px;
                        display: flex;
                        align-items: center;
                        justify-content: center;
                        gap: 15px;
                    }
                    
                    .header p {
                        font-size: 1.1rem;
                        opacity: 0.9;
                        margin-bottom: 20px;
                    }
                    
                    .badge {
                        background: rgba(255,255,255,0.2);
                        padding: 5px 15px;
                        border-radius: 20px;
                        font-size: 0.9rem;
                        display: inline-block;
                    }
                    
                    .content {
                        padding: 40px;
                    }
                    
                    .section {
                        margin-bottom: 30px;
                    }
                    
                    .section h2 {
                        color: #333;
                        margin-bottom: 20px;
                        padding-bottom: 10px;
                        border-bottom: 2px solid #f0f0f0;
                        display: flex;
                        align-items: center;
                        gap: 10px;
                    }
                    
                    .endpoint {
                        background: #f8f9fa;
                        border-left: 4px solid #667eea;
                        padding: 15px;
                        margin: 10px 0;
                        border-radius: 8px;
                        transition: all 0.3s ease;
                    }
                    
                    .endpoint:hover {
                        background: #eef2ff;
                        transform: translateX(5px);
                    }
                    
                    .method {
                        display: inline-block;
                        padding: 4px 12px;
                        border-radius: 4px;
                        font-weight: bold;
                        font-size: 0.9rem;
                        margin-right: 10px;
                    }
                    
                    .get { background: #61affe; color: white; }
                    .post { background: #49cc90; color: white; }
                    .put { background: #fca130; color: white; }
                    .delete { background: #f93e3e; color: white; }
                    
                    .path {
                        font-family: 'Courier New', monospace;
                        font-size: 1rem;
                        color: #333;
                    }
                    
                    .description {
                        margin-top: 8px;
                        color: #666;
                        font-size: 0.95rem;
                    }
                    
                    .test-link {
                        display: inline-block;
                        margin-top: 10px;
                        padding: 8px 16px;
                        background: #667eea;
                        color: white;
                        text-decoration: none;
                        border-radius: 6px;
                        font-size: 0.9rem;
                        transition: background 0.3s ease;
                    }
                    
                    .test-link:hover {
                        background: #5a67d8;
                    }
                    
                    .links {
                        display: flex;
                        gap: 15px;
                        margin-top: 20px;
                        flex-wrap: wrap;
                    }
                    
                    .link-btn {
                        padding: 12px 24px;
                        background: #f0f0f0;
                        border-radius: 8px;
                        text-decoration: none;
                        color: #333;
                        display: flex;
                        align-items: center;
                        gap: 8px;
                        transition: all 0.3s ease;
                    }
                    
                    .link-btn:hover {
                        background: #667eea;
                        color: white;
                        transform: translateY(-2px);
                    }
                    
                    .footer {
                        background: #f8f9fa;
                        padding: 20px 40px;
                        text-align: center;
                        color: #666;
                        border-top: 1px solid #e0e0e0;
                    }
                    
                    .tech-stack {
                        display: flex;
                        justify-content: center;
                        gap: 15px;
                        margin-top: 10px;
                        flex-wrap: wrap;
                    }
                    
                    .tech-badge {
                        background: #e9ecef;
                        padding: 5px 12px;
                        border-radius: 15px;
                        font-size: 0.85rem;
                        color: #495057;
                    }
                    
                    @media (max-width: 768px) {
                        .content {
                            padding: 20px;
                        }
                        
                        .header h1 {
                            font-size: 2rem;
                        }
                    }
                </style>
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
            </head>
            <body>
                <div class="container">
                    <div class="header">
                        <h1>
                            <i class="fas fa-shopping-cart"></i>
                            ECommerce REST API
                        </h1>
                        <p>Complete Item Management System for Freelance Java Developer Task</p>
                        <div class="badge">
                            <i class="fas fa-rocket"></i> Live & Production Ready
                        </div>
                    </div>
                    
                    <div class="content">
                        <div class="section">
                            <h2><i class="fas fa-bullseye"></i> Project Overview</h2>
                            <p style="color: #666; line-height: 1.6; margin-bottom: 20px;">
                                A Spring Boot RESTful API for managing e-commerce items with full CRUD operations, 
                                built as a sample task for Freelance Java Developer opportunity. 
                                Features dual storage implementation (ArrayList + H2 Database) and comprehensive validation.
                            </p>
                        </div>
                        
                        <div class="section">
                            <h2><i class="fas fa-plug"></i> API Endpoints</h2>
                            
                            <div class="endpoint">
                                <span class="method get">GET</span>
                                <span class="path">/api/v1/items/health</span>
                                <div class="description">Health check endpoint to verify API is running</div>
                                <a href="/api/v1/items/health" class="test-link">
                                    <i class="fas fa-heartbeat"></i> Test Health
                                </a>
                            </div>
                            
                            <div class="endpoint">
                                <span class="method get">GET</span>
                                <span class="path">/api/v1/items</span>
                                <div class="description">Get all items from H2 Database</div>
                                <a href="/api/v1/items" class="test-link">
                                    <i class="fas fa-list"></i> View All Items
                                </a>
                            </div>
                            
                            <div class="endpoint">
                                <span class="method get">GET</span>
                                <span class="path">/api/v1/items/{id}</span>
                                <div class="description">Get specific item by ID (Required Task)</div>
                            </div>
                            
                            <div class="endpoint">
                                <span class="method post">POST</span>
                                <span class="path">/api/v1/items</span>
                                <div class="description">Create new item in H2 Database</div>
                            </div>
                            
                            <div class="endpoint">
                                <span class="method post">POST</span>
                                <span class="path">/api/v1/arraylist/items</span>
                                <div class="description">Create item using ArrayList storage (Required Task)</div>
                                <a href="/api/v1/arraylist/items" class="test-link">
                                    <i class="fas fa-database"></i> View ArrayList Items
                                </a>
                            </div>
                            
                            <div class="endpoint">
                                <span class="method get">GET</span>
                                <span class="path">/api/v1/arraylist/items/{id}</span>
                                <div class="description">Get item by ID from ArrayList storage (Required Task)</div>
                            </div>
                        </div>
                        
                        <div class="section">
                            <h2><i class="fas fa-link"></i> Quick Links</h2>
                            <div class="links">
                                <a href="https://github.com/Manjula1042004/ecommerce-rest-api" class="link-btn" target="_blank">
                                    <i class="fab fa-github"></i> GitHub Repository
                                </a>
                                <a href="/api/v1/items" class="link-btn">
                                    <i class="fas fa-code"></i> JSON API
                                </a>
                                <a href="https://github.com/Manjula1042004/ecommerce-rest-api/blob/main/README.md" class="link-btn" target="_blank">
                                    <i class="fas fa-book"></i> Documentation
                                </a>
                            </div>
                        </div>
                    </div>
                    
                    <div class="footer">
                        <p>Built for Freelance Java Developer Opportunity</p>
                        <div class="tech-stack">
                            <span class="tech-badge">Spring Boot 4.0.2</span>
                            <span class="tech-badge">Java 21</span>
                            <span class="tech-badge">H2 Database</span>
                            <span class="tech-badge">Maven</span>
                            <span class="tech-badge">REST API</span>
                            <span class="tech-badge">Render.com</span>
                        </div>
                        <p style="margin-top: 15px; font-size: 0.9rem;">
                            Created by Manjula M J • Task Submission
                        </p>
                    </div>
                </div>
                
                <script>
                    // Add smooth scrolling for anchor links
                    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
                        anchor.addEventListener('click', function (e) {
                            e.preventDefault();
                            const targetId = this.getAttribute('href');
                            if(targetId !== '#') {
                                const targetElement = document.querySelector(targetId);
                                if(targetElement) {
                                    targetElement.scrollIntoView({ behavior: 'smooth' });
                                }
                            }
                        });
                    });
                    
                    // Add animation to endpoints on page load
                    document.addEventListener('DOMContentLoaded', function() {
                        const endpoints = document.querySelectorAll('.endpoint');
                        endpoints.forEach((endpoint, index) => {
                            endpoint.style.opacity = '0';
                            endpoint.style.transform = 'translateX(-20px)';
                            
                            setTimeout(() => {
                                endpoint.style.transition = 'all 0.5s ease';
                                endpoint.style.opacity = '1';
                                endpoint.style.transform = 'translateX(0)';
                            }, index * 100);
                        });
                    });
                </script>
            </body>
            </html>
            """;
        
        return new ResponseEntity<>(html, HttpStatus.OK);
    }
}
